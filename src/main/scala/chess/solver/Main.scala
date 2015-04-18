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
  if (args.nonEmpty) {
    bench(new Board(width = args(0).toInt, height = args(1).toInt), args.toList.drop(2).map(c => Figures(c.head)))
  }
}
