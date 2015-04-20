package chess.solver.board

import chess.solver.Figures.Figure

class Board(
             val width: Int,
             val height: Int,
             val figuresOnFields: Map[Field, Figure] = Map.empty,
             currentFields: List[Field] = Nil) {

  val fields: List[Field] =
    if (currentFields.isEmpty)
      ( for {
        xd <- 0 to width - 1
        yd <- 0 to height - 1
      } yield Field(xd, yd)).toList
    else
      currentFields

  def threatenedFields = {
    figuresOnFields.flatMap {
      case (field, figure) =>
        figure(field, fields).filter(inbound)
    } ++ figuresOnFields.keys
  }

  def inbound(target: Field): Boolean = {
    0 <= target.y && target.y < height && 0 <= target.x && target.x < width
  }

  val safeFields = fields.filter(f => !threatenedFields.toSeq.contains(f)).toList

  def createPossibleBranches(figure: Figure, remainingFigures: Int): List[Board] = {
    //small optimization, if there is less fields than remaining figures, this branch is already invalid
    if (safeFields.size < remainingFigures)
      Nil
    else
      safeFields.flatMap(createBranch(figure, _))
  }

  def createBranch(figure: Figure, field: Field): Option[Board] = {

    val threatenedByNewFigure = figure(field, fields).filter(inbound)

    if (!threatenedFields.toList.contains(field) && endangersCurrentFigures(threatenedByNewFigure)) {
      val newField = field -> figure

      val board = new Board(width, height, figuresOnFields + newField, safeFields)
      Some(board)
    } else
      None
  }

  def endangersCurrentFigures(threatenedByNewFigure: List[Field]): Boolean = {
    figuresOnFields.keySet.forall(f => !threatenedByNewFigure.contains(f))
  }
}