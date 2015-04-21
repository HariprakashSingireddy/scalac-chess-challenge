package chess.solver

import java.util.Calendar

import chess.solver.Figures._
import chess.solver.board.Board

object Main extends App {

  def bench(board: Board, cases: Seq[Figure]) = {
//    println(s"${board.width} x ${board.height} ${cases.mkString(",")}")
    println("Press Enter to start")

    System.in.read

    println(Calendar.getInstance().getTime)

    val result = Timer {
      Solver(board, cases)
    }

    result.take(16).foreach(println)

    println(result.size)
  }

  if (args.nonEmpty) {
    bench(Board(width = args(0).toInt, height = args(1).toInt), args.toList.drop(2).map(c => Figures(c.head)))
  }
  else {
    //  7x7 board with 2 Kings, 2 Queens, 2 Bishops and 1 Knight
    bench(Board(width = 7, height = 7), Seq(King, King, Queen, Queen, Bishop, Bishop, Knight))
  }
}