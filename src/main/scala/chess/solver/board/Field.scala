package chess.solver.board

case class Field(x: Int, y: Int) {
  def move(movementVector: (Int, Int)) = {

    Field(x + movementVector._1, y + movementVector._2)
  }
}
