package chess.solver

import scala.annotation.tailrec

package object board {
  @tailrec
  def checkFiguresOnBoard(board: Board, list: List[(Field, Figures.Figure)]): Boolean = {
    if (list.nonEmpty) {
      board.put(list.head._2, list.head._1) match {
        case Some(b) => checkFiguresOnBoard(b, list.tail)
        case None => false
      }
    }
    else true
  }
}
