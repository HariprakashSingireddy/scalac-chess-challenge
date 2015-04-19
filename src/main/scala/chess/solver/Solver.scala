package chess.solver

import chess.solver.board._

object Solver {

  def apply(board: Board, figures: Seq[Figures.Figure]): Seq[Map[Field, Figures.Figure]] = {
    val sortedFigures = figures.sortBy(-_.influence)


    val generation1 = board.putOnFirstSafe(sortedFigures.head, sortedFigures.size)
    val remainingFigures = sortedFigures.tail

    val remainingCount = remainingFigures.size

    val w = remainingFigures.zipWithIndex.foldLeft(generation1) {
      case (gen, (figure, index)) =>
        gen.flatMap(_.putOnFirstSafe(figure, remainingCount - index))
    }

    w.map(_.figuresOnFields).distinct
  }
}
