package chess.solver

import chess.solver.board.Field

object Figures {

  def apply(c: Char) = {
    c.toUpper match {
      case 'K' => King
      case 'Q' => Queen
      case 'R' => Rook
      case 'B' => Bishop
      case 'N' => Knight
    }
  }

  sealed trait Figure extends ((Field, Set[Field]) => Set[Field]) {
    val symbol: Char
    val influence: Int

    def filter(current: Field, checked: Field): Boolean

    def apply(field: Field, available: Set[Field]): Set[Field] = {
      available.filter { checkedField => filter(field, checkedField) }
    }

    override def toString() = symbol.toString
  }

  object King extends Figure {
    val symbol = 'K'
    val influence = 1

    def filter(current: Field, checked: Field) = {
      Math.abs(current.x - checked.x) <= 1 && Math.abs(current.y - checked.y) <= 1
    }
  }

  object Queen extends Figure {
    val symbol = 'Q'
    val influence = 5

    def filter(current: Field, checked: Field): Boolean = {
      current.x == checked.x || current.y == checked.y ||
        Math.abs(current.x - checked.x) == Math.abs(current.y - checked.y)
    }
  }

  object Rook extends Figure {
    val symbol = 'R'
    val influence = 3

    def filter(current: Field, checked: Field): Boolean = {
      current.x == checked.x || current.y == checked.y
    }
  }

  object Bishop extends Figure {
    val symbol = 'B'

    val influence = 4

    override def filter(current: Field, checked: Field): Boolean = {
      Math.abs(current.x - checked.x) == Math.abs(current.y - checked.y)
    }
  }

  object Knight extends Figure {
    val symbol = 'N'
    val influence = 2

    override def filter(current: Field, checked: Field): Boolean = {
      (Math.abs(current.x - checked.x) == 1 && Math.abs(current.y - checked.y) == 2) ||
        (Math.abs(current.x - checked.x) == 2 && Math.abs(current.y - checked.y) == 1)
    }
  }

}
