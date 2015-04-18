package chess.solver.board

import chess.solver.Figures.Figure

class Board(val width: Int, val height: Int, val figuresOnFields: Map[Field, Figure] = Map.empty) {

  val fields: Set[Field] =
    (for {
      xd <- 0 to width - 1
      yd <- 0 to height - 1
    } yield Field(xd, yd)).toSet

  def threatenedFields: Set[Field] = {
    figuresOnFields.flatMap {
      case (field, figure) =>
        figure(field, this)
    }.toSet
  }

  def safeFields: Set[Field] = fields -- threatenedFields -- figuresOnFields.keys

  def checkMoveLegality(target: Field): Boolean = {
    val result = 0 <= target.y && target.y < height && 0 <= target.x && target.x < width
    result
  }

  def put(figure: Figure, field: Field): Option[Board] = {

    val threatenedByNewFigure = figure(field, this)

    if ((safeFields contains field) && endangersCurrentFigures(threatenedByNewFigure)) {
      val newField = field -> figure

      val board = new Board(width, height, figuresOnFields + newField)
      Some(board)
    } else
      None
  }

  def endangersCurrentFigures(threatenedByNewFigure: Set[Field]): Boolean = {
    !figuresOnFields.keySet.exists(f => threatenedByNewFigure contains f)
  }
}