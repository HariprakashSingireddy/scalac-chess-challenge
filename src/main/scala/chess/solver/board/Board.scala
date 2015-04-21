package chess.solver.board

import chess.solver.Figures.Figure

object Board {
  def apply(width: Int, height: Int): Board = {

    val allFields = for {
      xd <- 0 to width - 1
      yd <- 0 to height - 1
    } yield Field(xd, yd)

    new Board(allFields.toList, Map.empty)
  }
}


case class Board(fields: List[Field],
                 figuresOnFields: Map[Field, Figure]) {
  def occupied = figuresOnFields.keys

  val figureCount = figuresOnFields.size
}

case class Field(x: Int, y: Int)