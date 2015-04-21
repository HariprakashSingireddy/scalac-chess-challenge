import chess.solver.Figures._
import chess.solver.Solver
import chess.solver.board.Board


import org.scalameter.api._

object PerformanceSpec extends PerformanceTest.Quickbenchmark {
  val chess = Gen.enumeration("input")(
    Board(7, 7) -> Seq(King, King, Queen, Queen, Bishop, Bishop, Knight),
    Board(8, 8) -> Seq(Queen, Queen, Queen, Queen, Queen, Queen, Queen, Queen),
    Board(4, 4) -> Seq(Rook, Rook, Knight, Knight, Knight, Knight),
    Board(3, 3) -> Seq(Rook, King, King)
  )

  performance of "Solver" in {
    using(chess) in {
      case (board, figures) => Solver(board, figures)
    }
  }
}

