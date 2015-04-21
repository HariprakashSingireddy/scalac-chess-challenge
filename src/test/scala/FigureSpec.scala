import chess.solver.Figures._
import chess.solver.board.{Board, Field}
import org.scalatest.{FlatSpec, Matchers}

class FigureSpec extends FlatSpec with Matchers {

  val board = Board(3, 3)

  def check = board.fields.filter _

  behavior of "The King"

  it should "standing in 0,0 be able to move to 0,1; 1,1; 1,0" in {
    check(King(Field(0, 0), _)) should contain theSameElementsAs
      Set(Field(0, 1), Field(1, 1), Field(1, 0), Field(0, 0))
  }

  it should "standing in 1,1 be able to move to 0,0;0,1;0,2;1,2;2,2;2,1;2,0;1,0" in {
    check(King(Field(1, 1), _)) should contain theSameElementsAs
      Set(Field(0, 0), Field(0, 1), Field(0, 2), Field(1, 2), Field(2, 2), Field(2, 1), Field(2, 0), Field(1, 0), Field(1, 1))
  }

  behavior of "The Queen"
  it should "standing in 0,0 be able to move to 0,1; 1,1; 1,0; 0,2; 2,2; 2,0" in {
    check(Queen(Field(0, 0), _)) should contain theSameElementsAs
      Set(Field(0, 1), Field(1, 1), Field(1, 0), Field(0, 2), Field(2, 2), Field(2, 0), Field(0, 0))
  }

  it should "standing in 1,1 be able to move to 0,0;0,1;0,2;1,2;2,2;2,1;2,0;1,0" in {
    check(Queen(Field(1, 1), _)) should contain theSameElementsAs
      Set(Field(0, 0), Field(0, 1), Field(0, 2), Field(1, 2), Field(2, 2), Field(2, 1), Field(2, 0), Field(1, 0), Field(1, 1))
  }

  behavior of "The Rook"
  it should "standing in 0,0 be able to move to 0,1;0,2;1,0;2,0" in {
    check(Rook(Field(0, 0), _)) should contain theSameElementsAs
      Set(Field(0, 1), Field(0, 2), Field(1, 0), Field(2, 0), Field(0, 0))
  }

  it should "standing in 1,1 be able to move to 0,1;2,1;1,0;1,2" in {
    check(Rook(Field(1, 1), _)) should contain theSameElementsAs
      Set(Field(0, 1), Field(2, 1), Field(1, 0), Field(1, 2), Field(1, 1))
  }

  behavior of "The Bishop"
  it should "standing in 0,0 be able to move to 1,1;2,2" in {
    check(Bishop(Field(0, 0), _)) should contain theSameElementsAs
      Set(Field(1, 1), Field(2, 2), Field(0, 0))
  }

  it should "standing in 1,1 be able to move to 0,0;2,2;2,0;0,2" in {
    check(Bishop(Field(1, 1), _)) should contain theSameElementsAs
      Set(Field(0, 0), Field(2, 2), Field(0, 2), Field(2, 0), Field(1, 1))
  }

  behavior of "The Knight"
  it should "standing in 0,0 be able to move to 1,2;2.1" in {
    check(Knight(Field(0, 0), _)) should contain theSameElementsAs
      Set(Field(1, 2), Field(2, 1))
  }

  it should "standing in 1,1 be unable to move" in {
    check(Knight(Field(1, 1), _)) shouldBe empty
  }

  it should "standing in 2,2 be able to move to 0,1;1,0" in {
    check(Knight(Field(2, 2), _)) should contain theSameElementsAs
      Set(Field(0, 1), Field(1, 0))
  }
}
