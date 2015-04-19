import chess.solver.Figures.King
import chess.solver.board.{Field, Board}
import org.scalatest.{Matchers, FlatSpec}

class BoardSpec extends FlatSpec with Matchers {
  behavior of "the Board"

  var board = new Board(2, 2)

  it should "create all fields" in {
    board.fields should contain theSameElementsAs
      Set(Field(0, 0), Field(0, 1), Field(1, 1), Field(1, 0))
  }

  it should "indicate threatened fields" in {
    board = board.put(King, Field(0, 0)).get
    board.threatenedFields should contain theSameElementsAs
      Set(Field(0, 1), Field(1, 1), Field(1, 0), Field(0, 0))

  }

  it should "invalidate the board if put a invalid figure" in {
    val boardOpt = for {
      boardWithKing <- board.put(King, Field(0, 1))
      boardWith2Kings <- boardWithKing.put(King, Field(0, 0))
    } yield boardWith2Kings

    boardOpt should be('empty)
  }
}
