package chess.solver.board

case class Field(x: Int, y: Int) {
  def apply(xd: Int, yd: Int) = {

    Field(x + xd, y + yd)
  }
}
