package chess.solver.board

case class Field(x: Int, y: Int) {
  def apply(movementVector: (Int, Int)) = {

    Field(x + movementVector._1, y + movementVector._2)
  }
}
