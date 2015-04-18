package chess.solver.board

class Board(val width: Int, val height: Int) {

  def checkMoveLegality(target: Field): Boolean = {
    val result = 0 <= target.y && target.y < height && 0 <= target.x && target.x < width
    result
  }
}