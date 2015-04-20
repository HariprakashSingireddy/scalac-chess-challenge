package chess.solver

import chess.solver.board._

import scala.annotation.tailrec
import scala.collection.parallel.ParSeq

object Solver {

  def apply(board: Board, figures: Seq[Figures.Figure]): Seq[Map[Field, Figures.Figure]] = {
    val sortedFigures = figures.sortBy(-_.influence)


    val root = board.putOnFirstSafe(sortedFigures.head, sortedFigures.size)
    val remainingFigures = sortedFigures.tail
    val w = root.zipWithIndex.map { case (g, i) =>
      val d = Timer(s"$i.") {
        explore((g :: Nil).par, remainingFigures).map(_.figuresOnFields).distinct.toList
      }
      d
    }
    w.flatten.distinct
  }

  @tailrec
  def explore(xs: ParSeq[Board], remainingFigures: Seq[Figures.Figure]): ParSeq[Board] = {
    if (remainingFigures.nonEmpty) {
      val q = xs.par.flatMap(_.putOnFirstSafe(remainingFigures.head, remainingFigures.size)).distinct
      explore(q, remainingFigures.tail)
    }
    else
      xs
  }

}
