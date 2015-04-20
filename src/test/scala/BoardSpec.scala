import chess.solver.Figures.{Queen, King}
import chess.solver.board.{Field, Board}
import org.scalatest.{Matchers, FlatSpec}

class BoardSpec extends FlatSpec with Matchers {
  behavior of "the Board"

  var board =  Board(2, 2)

  it should "create all fields" in {
    board.fields should contain allOf
      (Field(0, 0), Field(0, 1), Field(1, 1), Field(1, 0))

    board.fields.size shouldBe 4
  }

  it should "indicate threatened fields" in {
    board = board.createBranch(King, Field(0, 0)).get
    board.fields should contain noneOf
      (Field(0, 1), Field(1, 1), Field(1, 0), Field(0, 0))

  }

  it should "invalidate the board if put a invalid figure" in {
    val boardOpt = for {
      boardWithKing <- board.createBranch(Queen, Field(2, 1))
      boardWith2Kings <- boardWithKing.createBranch(King, Field(0, 0))
    } yield boardWith2Kings

    boardOpt should be('empty)
  }
}
