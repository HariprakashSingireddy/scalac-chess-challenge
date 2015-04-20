import chess.solver.Figures.{King, Queen}
import chess.solver.board.{Board, Field}
import org.scalatest.{FlatSpec, Matchers}

class SafePlaceTest extends FlatSpec with Matchers {
  behavior of "the Solver"

  var board = Board(7, 7)
  val branches = board.createPossibleBranches(Queen, 1)

  it should "in should determine branch for field" in {
    branches should have size 49
  }

  val branch = branches.head
  it should "assign the a branch appropriate number of fields" in {
    branch.fields should have size 49 - 19
  }

  behavior of "a branch"

  it should "put the next figure in a safe place" in {
    branch.fields.zip(branch.createPossibleBranches(Queen, 1)).foreach { case (safePlace, br) =>
      br.figuresOnFields(safePlace) shouldBe Queen
    }
  }
}
