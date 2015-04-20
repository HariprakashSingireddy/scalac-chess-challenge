package chess.solver

import chess.solver.Figures._
import chess.solver.board.Board


object Main extends App {

  def bench(board: Board, cases: Seq[Figure]) = {
    println(s"${board.width} x ${board.height} ${cases.mkString(",")}")
    println("Press any key to start")

    System.in.read
    val result = Timer {
      Solver(board, cases)
    }
//    result.foreach(println)
    println(result.size)
  }

  //  //  7×7 board with 2 Kings, 2 Queens, 2 Bishops and 1 Knight
  if (args.nonEmpty) {
    bench(new Board(width = args(0).toInt, height = args(1).toInt), args.toList.drop(2).map(c => Figures(c.head)))
  }
}
