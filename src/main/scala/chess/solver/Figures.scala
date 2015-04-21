package chess.solver


object Figures {

  import chess.solver.board.Field
  import java.lang.Math.abs

  def apply(c: Char) = {
    c.toUpper match {
      case 'K' => King
      case 'Q' => Queen
      case 'R' => Rook
      case 'B' => Bishop
      case 'N' => Knight
    }
  }

  sealed trait Figure {
    val symbol: Char

    def apply(current: Field, checked: Field): Boolean

    override def toString = symbol.toString

    override def hashCode = symbol.toInt
  }

  object King extends Figure {
    val symbol = 'K'

    def apply(current: Field, checked: Field) = {
      abs(current.x - checked.x) <= 1 && abs(current.y - checked.y) <= 1
    }
  }


  object Queen extends Figure {
    val symbol = 'Q'

    def apply(current: Field, checked: Field): Boolean = {
      current.x == checked.x || current.y == checked.y ||
        abs(current.x - checked.x) == abs(current.y - checked.y)
    }
  }

  object Rook extends Figure {
    val symbol = 'R'

    def apply(current: Field, checked: Field): Boolean = {
      current.x == checked.x || current.y == checked.y
    }
  }

  object Bishop extends Figure {
    val symbol = 'B'

    def apply(current: Field, checked: Field): Boolean = {
      abs(current.x - checked.x) == abs(current.y - checked.y)
    }
  }

  object Knight extends Figure {
    val symbol = 'N'

    def apply(current: Field, checked: Field): Boolean = {
      (abs(current.x - checked.x) == 1 && abs(current.y - checked.y) == 2) ||
        (abs(current.x - checked.x) == 2 && abs(current.y - checked.y) == 1)
    }
  }

}
