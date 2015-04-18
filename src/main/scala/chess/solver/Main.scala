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


  //  //  7×7 board with 2 Kings, 2 Queens, 2 Bishops and 1 Knight
  //    val board = new Board(width = 5, height = 6)
  //    val cases = Seq(Knight, Knight, Queen, Queen, Bishop, Bishop, Knight)
  {
    val board = new Board(width = 3, height = 3)
    val cases = Seq(King, King, Rook)
    println(s"3 x 3 ${cases.mkString(",")}")
    time {
      Solver(board, cases)
    }
  }
  {
    val board = new Board(width = 4, height = 4)
    val cases = Seq(Knight, Knight, Knight, Knight, Rook, Rook)
    println(s"4 x 4 ${cases.mkString(",")}")
    time {
      Solver(board, cases)
    }
  }

}
