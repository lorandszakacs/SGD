package com.lorandszakacs.util.files

import java.io.{File, FileFilter, IOException, PrintWriter}
import java.nio.file._
import java.nio.file.attribute.BasicFileAttributes

import com.lorandszakacs.util.effects._

import com.lorandszakacs.util.logger._

import scala.collection.mutable

/**
  *
  * @author Lorand Szakacs, lsz@lorandszakacs.com
  * @since 17 Jul 2016
  *
  */
object FileUtils {
  implicit private val logger: Logger[IO] = Logger.getLogger[IO]

  def normalizeHomePath(path: String): String = {
    path.replaceFirst("^~", System.getProperty("user.home"))
  }

  /**
    * recursively deletes everything in the specified folder
    */
  def cleanFolderOrCreate(fd: Path): IO[Unit] =
    IO {
      fd.toAbsolutePath.toFile.mkdirs()
      Files.walkFileTree(
        fd,
        new FileVisitor[Path] {
          override def visitFileFailed(file: Path, exc: IOException): FileVisitResult = {
            if (file.toAbsolutePath == fd.toAbsolutePath) {
              throw RootFolderCouldNotBeOpenedException(fd.toAbsolutePath.toString, exc)
            }
            else {
              FileVisitResult.CONTINUE
            }
          }

          override def visitFile(file: Path, attrs: BasicFileAttributes): FileVisitResult = {
            if (Files.isDirectory(file)) {
              //in this case, the folder will be deleted in the postVisitDirectory, once the folder is deleted.
              FileVisitResult.CONTINUE
            }
            else {
              Try(Files.delete(file)) match {
                case TryFailure(exception) =>
                  throw RootFolderFileCouldNotBeDeleted(file.toAbsolutePath.toString, exception)
                case TrySuccess(_) => ()
              }
              FileVisitResult.CONTINUE
            }
          }

          override def preVisitDirectory(dir: Path, attrs: BasicFileAttributes): FileVisitResult = {
            FileVisitResult.CONTINUE

          }

          override def postVisitDirectory(dir: Path, exc: IOException): FileVisitResult = {
            if (dir.toAbsolutePath != fd.toAbsolutePath) {
              Files.deleteIfExists(dir)
              FileVisitResult.CONTINUE
            }
            else {
              FileVisitResult.CONTINUE
            }
          }
        },
      )
    } >> logger.info(s"successfully cleaned: ${fd.toAbsolutePath}")

  def fileMatchInEverythingButDate(s1: String, s2: String): Boolean = {
    if (s1.length != s2.length)
      false
    else {
      val zip = s1.zip(s2)
      val set: Set[Char] = zip.filterNot(p => p._1 == p._2).map(p => Set(p._1, p._2)).toSet.flatten
      val setOfNumber  = Set('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')
      val intersection = set.diff(setOfNumber)
      set.nonEmpty && intersection.isEmpty
    }
  }

  def findPotentialDuplicates(fd: Path): IO[Set[Set[String]]] = IO {
    def tailsMatch(s1: String, s2: String): Boolean = {
      fileMatchInEverythingButDate(s1, s2)
    }

    val acc = mutable.Set[Set[String]]()
    fd.toAbsolutePath.toFile.mkdirs()
    Files.walkFileTree(
      fd,
      new FileVisitor[Path] {

        override def visitFileFailed(file: Path, exc: IOException): FileVisitResult = {
          if (file.toAbsolutePath == fd.toAbsolutePath) {
            throw RootFolderCouldNotBeOpenedException(fd.toAbsolutePath.toString, exc)
          }
          else {
            FileVisitResult.CONTINUE
          }
        }

        override def visitFile(file: Path, attrs: BasicFileAttributes): FileVisitResult = {
          FileVisitResult.CONTINUE
        }

        override def preVisitDirectory(dir: Path, attrs: BasicFileAttributes): FileVisitResult = {
          val dirFile = dir.toFile
          val onlyFileFilter = new FileFilter {
            override def accept(pathname: File): Boolean = !pathname.isDirectory
          }
          val f: Array[File] = dirFile.listFiles(onlyFileFilter)
          val fileNames = f.map(_.toPath.toAbsolutePath.toString)
          val x: Array[Set[String]] = for {
            f1 <- fileNames
            f2 <- fileNames
            if f1 != f2 && tailsMatch(f1, f2)
          } yield Set(f1, f2)
          val r: Set[String] = x.flatten.toSet
          acc += r
          FileVisitResult.CONTINUE

        }

        override def postVisitDirectory(dir: Path, exc: IOException): FileVisitResult = {
          FileVisitResult.CONTINUE
        }
      },
    )
    acc.toSet
  }

  def createFolders(fd: Path): IO[Unit] = {
    val f = fd.toAbsolutePath.toFile
    for {
      result <- IO(f.mkdirs())
      _      <- result.ifFalseRaise[IO](FailedToCreateFolderException(f.getAbsolutePath))
    } yield ()
  }

  /**
    * Does NOT overwrite file!
    *
    */
  def writeFile(fp: Path, content: String): IO[Unit] = IO {
    val writer = new PrintWriter(fp.toAbsolutePath.toFile)
    Try(writer.write(content)) match {
      case TrySuccess(_) =>
        writer.close()
      case TryFailure(exception) =>
        writer.close()
        throw exception
    }
  }

  def overwriteFile(fp: Path, content: String): IO[Unit] = IO {
    val file = fp.toAbsolutePath.toFile
    if (file.exists()) {
      file.delete()
    }
    val writer = new PrintWriter(fp.toAbsolutePath.toFile)
    Try(writer.write(content)) match {
      case TrySuccess(_) =>
        writer.close()
      case TryFailure(exception) =>
        writer.close()
        throw exception
    }
  }
}
