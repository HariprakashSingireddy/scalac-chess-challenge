import chess.solver.Figures._
import chess.solver.board.{Board, Field}
import org.scalatest.{FlatSpec, Matchers}

class FigureSpec extends FlatSpec with Matchers {

  val board = Board(3, 3)


  behavior of "The King"
  it should "standing in 0,0 be able to move to 0,1; 1,1; 1,0" in {
    King(Field(0, 0), board.fields) should contain theSameElementsAs
      Set(Field(0, 1), Field(1, 1), Field(1, 0), Field(0, 0))
  }

  it should "standing in 1,1 be able to move to 0,0;0,1;0,2;1,2;2,2;2,1;2,0;1,0" in {
    King(Field(1, 1), board.fields) should contain theSameElementsAs
      Set(Field(0, 0), Field(0, 1), Field(0, 2), Field(1, 2), Field(2, 2), Field(2, 1), Field(2, 0), Field(1, 0), Field(1, 1))
  }

  behavior of "The Queen"
  it should "standing in 0,0 be able to move to 0,1; 1,1; 1,0; 0,2; 2,2; 2,0" in {
    Queen(Field(0, 0), board.fields) should contain theSameElementsAs
      Set(Field(0, 1), Field(1, 1), Field(1, 0), Field(0, 2), Field(2, 2), Field(2, 0), Field(0, 0))
  }

  it should "standing in 1,1 be able to move to 0,0;0,1;0,2;1,2;2,2;2,1;2,0;1,0" in {
    Queen(Field(1, 1), board.fields) should contain theSameElementsAs
      Set(Field(0, 0), Field(0, 1), Field(0, 2), Field(1, 2), Field(2, 2), Field(2, 1), Field(2, 0), Field(1, 0), Field(1, 1))
  }

  behavior of "The Rook"
  it should "standing in 0,0 be able to move to 0,1;0,2;1,0;2,0" in {
    Rook(Field(0, 0), board.fields) should contain theSameElementsAs
      Set(Field(0, 1), Field(0, 2), Field(1, 0), Field(2, 0), Field(0, 0))
  }

  it should "standing in 1,1 be able to move to 0,1;2,1;1,0;1,2" in {
    Rook(Field(1, 1), board.fields) should contain theSameElementsAs
      Set(Field(0, 1), Field(2, 1), Field(1, 0), Field(1, 2), Field(1, 1))
  }

  behavior of "The Bishop"
  it should "standing in 0,0 be able to move to 1,1;2,2" in {
    Bishop(Field(0, 0), board.fields) should contain theSameElementsAs
      Set(Field(1, 1), Field(2, 2), Field(0, 0))
  }

  it should "standing in 1,1 be able to move to 0,0;2,2;2,0;0,2" in {
    Bishop(Field(1, 1), board.fields) should contain theSameElementsAs
      Set(Field(0, 0), Field(2, 2), Field(0, 2), Field(2, 0), Field(1, 1))
  }

  behavior of "The Knight"
  it should "standing in 0,0 be able to move to 1,2;2.1" in {
    Knight(Field(0, 0), board.fields) should contain theSameElementsAs
      Set(Field(1, 2), Field(2, 1))
  }

  it should "standing in 1,1 be unable to move" in {
    Knight(Field(1, 1), board.fields) shouldBe empty
  }

  it should "standing in 2,2 be able to move to 0,1;1,0" in {
    Knight(Field(2, 2), board.fields) should contain theSameElementsAs
      Set(Field(0, 1), Field(1, 0))
  }
}
