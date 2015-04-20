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

  sealed trait Figure extends (Field => List[Field]) {
    val symbol: Char
    val influence: Int

    override def toString() = symbol.toString
  }

  object King extends Figure {
    val symbol = 'K'
    val influence = 1

    def apply(field: Field): List[Field] = {
      List(
        field(0, -1),
        field(0, 1),

        field(1, -1),
        field(1, 0),
        field(1, 1),

        field(-1, -1),
        field(-1, 0),
        field(-1, 1))

    }
  }

  object Queen extends Figure {
    val symbol = 'Q'
    val influence = 5

    def apply(field: Field): List[Field] = {
      (Rook(field) ++ Bishop(field)).filterNot(_ == field)
    }
  }

  object Rook extends Figure {
    val symbol = 'R'
    val influence = 3

    def apply(field: Field): List[Field] = {
      val xs = -7 to 7
      val ys = -7 to 7
      val horizontalMovement = xs map (x => field(x, 0))
      val verticalMovement = ys map (y => field(0, y))

      (horizontalMovement ++ verticalMovement)
        .filterNot(_ == field)
        .toList
    }
  }

  object Bishop extends Figure {
    val symbol = 'B'

    val influence = 4

    def apply(field: Field): List[Field] = {
      val range = 0 to 7
      val se = range map { r => field(r, r) }
      val ne = range map { r => field(-r, -r) }
      val sw = range map { r => field(-r, r) }
      val nw = range map { r => field(r, -r) }

      (se ++ ne ++ sw ++ nw)
        .filterNot(_ == field)
        .toList
    }
  }

  object Knight extends Figure {
    val symbol = 'N'
    val influence = 2

    def apply(field: Field): List[Field] = {
      List(
        field(2 -> 1),
        field(2, -1),

        field(1, 2),
        field(-1, 2),

        field(-2, 1),
        field(-2, -1),

        field(1, -2),
        field(-1, -2)
      )
    }
  }

}
