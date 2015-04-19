package chess.solver

import chess.solver.board.{Field, Board}

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

  sealed trait Figure extends ((Field, Board) => List[Field]) {
    val symbol: Char
    val influence: Int

    override def toString() = symbol.toString
  }

  object King extends Figure {
    val symbol = 'K'
    val influence = 1

    def apply(field: Field, board: Board): List[Field] = {
      List(
        field move(0, -1),
        field move(0, 1),

        field move(1, -1),
        field move(1, 0),
        field move(1, 1),

        field move(-1, -1),
        field move(-1, 0),
        field move(-1, 1)
      ).filter(board.checkMoveLegality)
    }
  }

  object Queen extends Figure {
    val symbol = 'Q'
    val influence = 5

    def apply(field: Field, board: Board): List[Field] = {
      (Rook(field, board) ++ Bishop(field, board)).filterNot(_ == field)
    }
  }

  object Rook extends Figure {
    val symbol = 'R'
    val influence = 3

    def apply(field: Field, board: Board): List[Field] = {
      val xs = -(board.width - 1) to board.width - 1
      val ys = -(board.height - 1) to board.height - 1
      val horizontalMovement = xs map (x => field move(x, 0))
      val verticalMovement = ys map (y => field move(0, y))

      (horizontalMovement ++ verticalMovement)
        .filter(board.checkMoveLegality)
        .filterNot(_ == field)
        .toList
    }
  }

  object Bishop extends Figure {
    val symbol = 'B'

    val influence = 4

    def apply(field: Field, board: Board): List[Field] = {
      val longerSide = if (board.width > board.height) board.width else board.height
      val range = 0 to longerSide
      val se = range map { r => field move(r, r) }
      val ne = range map { r => field move(-r, -r) }
      val sw = range map { r => field move(-r, r) }
      val nw = range map { r => field move(r, -r) }

      (se ++ ne ++ sw ++ nw)
        .filter(board.checkMoveLegality)
        .filterNot(_ == field)
        .toList
    }
  }

  object Knight extends Figure {
    val symbol = 'N'
    val influence = 2

    def apply(field: Field, board: Board): List[Field] = {
      List(
        field move(2, 1),
        field move(2, -1),

        field move(1, 2),
        field move(-1, 2),

        field move(-2, 1),
        field move(-2, -1),

        field move(1, -2),
        field move(-1, -2)
      ).filter(board.checkMoveLegality)
    }
  }

}
