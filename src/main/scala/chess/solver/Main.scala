package chess.solver

import chess.solver.Figures._
import chess.solver.board.{Field, Board}


object Main extends App {
  def time[R](block: => R): R = {
    val t0 = System.nanoTime()
    val result = block // call-by-name
    val t1 = System.nanoTime()

    println(s"Elapsed time: ${(t1 - t0) / 1000000000.0} s")
    result
  }

  //
  val board = new Board(width = 4, height = 4)
  val cases = Seq(Knight, Knight, Knight, Knight, Rook, Rook)

  //  7×7 board with 2 Kings, 2 Queens, 2 Bishops and 1 Knight
  //  val board = new Board(width = 7, height = 7)
  //  val cases = Seq(Knight, Knight, Queen, Queen, Bishop, Bishop, Knight)

  time {
    Solver(board, cases)
  }

}
