package chess.solver

import chess.solver.board._

import scala.annotation.tailrec
import scala.collection.parallel.ParSeq

object Solver {

  def apply(board: Board, figures: Seq[Figures.Figure]): ParSeq[Map[Field, Figures.Figure]] = {
    val sortedFigures = figures.sortBy(-_.influence)

    exploreBranch((board :: Nil).par, sortedFigures).map(_.figuresOnFields).distinct
  }

  @tailrec
  def exploreBranch(branches: ParSeq[Board], remainingFigures: Seq[Figures.Figure]): ParSeq[Board] = {
    if (remainingFigures.nonEmpty) {
      val branched = branches.flatMap(_.createPossibleBranches(remainingFigures.head, remainingFigures.size)).distinct
      exploreBranch(branched, remainingFigures.tail)
    }
    else
      branches
  }

}
