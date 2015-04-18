package chess.solver

import chess.solver.Figures._
import chess.solver.board.Board


object Main extends App {
  def time[R](block: => R): R = {
    val t0 = System.nanoTime()
    val result = block // call-by-name
    val t1 = System.nanoTime()

    println(s"Elapsed time: ${(t1 - t0) / 1000000000.0} s")
    result
  }

  def bench(board: Board, cases: Seq[Figure]) = {
    println(s"${board.width} x ${board.height} ${cases.mkString(",")}")
    val result = time {
      Solver(board, cases)
    }
    result.foreach(println)
    println(result.size)
  }

  //  //  7×7 board with 2 Kings, 2 Queens, 2 Bishops and 1 Knight
  //    val board = new Board(width = 5, height = 6)
  //    val cases = Seq(Knight, Knight, Queen, Queen, Bishop, Bishop, Knight)

  bench(new Board(width = 3, height = 3), Seq(King, King, Rook))

  bench(new Board(width = 4, height = 4), Seq(Knight, Knight, Knight, Knight, Rook, Rook))
  //
  bench(new Board(width = 6, height = 6), Seq(Knight, Knight, Knight, Knight, Rook, Rook))


}
