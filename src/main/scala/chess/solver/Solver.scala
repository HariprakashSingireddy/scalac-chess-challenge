package chess.solver

import chess.solver.board._

object Solver {
  def apply(board: Board, figures: Seq[Figures.Figure]): Seq[Map[Field, Figures.Figure]] = {
    val sortedFigures = figures.sortBy(-_.influence)

    val generation1 = board.putOnFirstSafe(sortedFigures.head).splitAt(4)._1
    val r = sortedFigures.tail
    val w = r.foldLeft(generation1) {
      case (gen, f) =>
        gen.flatMap(_.putOnFirstSafe(f))
    }
    w.map(_.figuresOnFields).distinct
  }
}
