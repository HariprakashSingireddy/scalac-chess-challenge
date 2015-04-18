package chess.solver

import chess.solver.board.{Field, Board}

object Figures {

  sealed trait Figure extends ((Field, Board) => Set[Field]) {
    val symbol: Char

    override def toString() = symbol.toString
  }

  object King extends Figure {
    val symbol = 'K'

    def apply(field: Field, board: Board): Set[Field] = {
      Set(
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

    def apply(field: Field, board: Board): Set[Field] = {
      Rook(field, board) ++ Bishop(field, board)
    }
  }

  object Rook extends Figure {
    val symbol = 'R'

    def apply(field: Field, board: Board): Set[Field] = {
      val xs = -(board.width - 1) to board.width - 1
      val ys = -(board.height - 1) to board.height - 1
      val horizontalMovement = xs map (x => field move(x, 0))
      val verticalMovement = ys map (y => field move(0, y))

      (horizontalMovement ++ verticalMovement).filter(board.checkMoveLegality).toSet
    }
  }

  object Bishop extends Figure {
    val symbol = 'B'

    def apply(field: Field, board: Board): Set[Field] = {
      val longerSide = if (board.width > board.height) board.width else board.height
      val range = -longerSide to longerSide
      val slashMovement = range map { r => field move(r, r) }
      val backslashMovement = range map { r => field move(-r, r) }

      (slashMovement ++ backslashMovement).filter(board.checkMoveLegality).toSet
    }
  }

  object Knight extends Figure {
    val symbol = 'N'

    def apply(field: Field, board: Board): Set[Field] = {
      Set(
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
