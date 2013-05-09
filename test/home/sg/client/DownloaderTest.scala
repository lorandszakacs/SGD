package home.sg.client

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import home.sg.parser.PhotoSetInfo

@RunWith(classOf[JUnitRunner])
class DownloaderTest extends FunSuite {

  test("download Sash") {
    downloadSG("Sash", new LevelOfReporting(1))
  }

  //  test("download Nahp") {
  //    downloadSG("Nahp")
  //  }
  //  
  //  test("download Nya") {
  //    downloadSG("Nya")
  //  }
  //  
  //  test("download Lyxzen") {
  //    downloadSG("Lyxzen")
  //  }

  private def downloadSG(sgName: String, lor: LevelOfReporting) {
    val rootFolder = "/Users/lorand/Downloads/temp/"
    val downloader = new Downloader("Sash", LoginInfo.user, LoginInfo.pwd, lor)
    downloader.download(rootFolder)
  }

  private def downloadSG(sgName: String) {
    downloadSG(sgName, new LevelOfReporting(0))
  }

}