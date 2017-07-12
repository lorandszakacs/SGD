package com.lorandszakacs.util.list

import com.lorandszakacs.util.math.Identity

/**
  *
  * @author Lorand Szakacs, lsz@lorandszakacs.com, lorand.szakacs@busymachines.com
  * @since 12 Jul 2017
  *
  */
trait ListUtilFunctions {

  implicit class BuffedList[T](thisList: List[T]) {

    /**
      * Replaces elements of [[thisList]] that identify with elements of ``that``
      * keeping all others the same
      */
    def replace(that: List[T])(implicit id: Identity[T]): List[T] = {
      if (that.isEmpty) {
        thisList
      } else {
        thisList.map { e =>
          val tr = that.find(l => id.identifiesAs(l, e))
          tr match {
            case None => e
            case Some(replacement) => replacement
          }
        }
      }
    }

    def addOrReplace(that: List[T])(implicit id: Identity[T]): List[T] = {
      if (that.isEmpty) {
        thisList
      } else {
        val (toReplace, toAdd) = that.partition(p => thisList.exists(e => id.identifiesAs(e, p)))
        val replaced = this.replace(toReplace)
        replaced ++ toAdd
      }

    }
  }

}
