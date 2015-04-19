package chess.solver

import chess.solver.board._

import scala.annotation.tailrec
import scala.collection.parallel.ParSeq

object Solver {
  def apply(board: Board, figures: Seq[Figures.Figure]): ParSeq[Map[Field, Figures.Figure]] = {
    val sortedFigures = figures.sortBy(-_.influence)

    val generation1 = board.putOnFirstSafe(sortedFigures.head).par

    val r = sortedFigures.tail
    val w = r.foldLeft(generation1) {
      case (gen, f) =>
        gen.flatMap(_.putOnFirstSafe(f))
    }
    w.map(_.figuresOnFields).distinct
  }


  //http://stackoverflow.com/a/1187445
  object Factorial {
    def apply(n: BigInt): BigInt = {
      @tailrec
      def factorialAcc(acc: BigInt, n: BigInt): BigInt = {
        if (n <= 1) acc
        else factorialAcc(n * acc, n - 1)
      }
      factorialAcc(1, n)
    }
  }

}
