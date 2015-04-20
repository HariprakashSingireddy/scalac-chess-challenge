package chess.solver.board

import chess.solver.Figures.Figure

object Board {
  var allFields: Set[Field] = Set()

  def apply(width: Int,
            height: Int,
            figuresOnFields: Map[Field, Figure] = Map.empty): Board = {

    allFields = (for {
      xd <- 0 to width - 1
      yd <- 0 to height - 1
    } yield Field(xd, yd)).toSet

    new Board(width, height, allFields, Map.empty)
  }

}

case class Board(
                  width: Int,
                  height: Int,
                  fields: Set[Field],
                  figuresOnFields: Map[Field, Figure]) {

  def createPossibleBranches(figure: Figure, remainingFigures: Int): Set[Board] = {
    //small optimization, if there is less fields than remaining figures, this branch is already invalid
    if (fields.size < remainingFigures)
      Set()
    else
      fields.flatMap(createBranch(figure, _))
  }

  def createBranch(figure: Figure, field: Field): Option[Board] = {

    val threatenedByNewFigure = figure(field, Board.allFields)
    val endangered = endangersCurrentFigures(threatenedByNewFigure)
    if (fields.contains(field) && endangered.isEmpty) {
      val newField = field -> figure

      val board = Board(width, height, fields -- threatenedByNewFigure, figuresOnFields + newField)
      Some(board)
    } else
      None
  }

  private def endangersCurrentFigures(threatenedByNewFigure: Set[Field]): Set[Field] = {
    figuresOnFields.keySet.intersect(threatenedByNewFigure)
  }
}