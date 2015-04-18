package chess.solver

import chess.solver.board._

import scala.annotation.tailrec

object Solver {
  case class Assignment(board: Board, figures: Seq[Figures.Figure])

  def apply(board: Board, figures: Seq[Figures.Figure]) = {
//    val combinationCount = Factorial(BigInt(board.fields.size)) / (Factorial(BigInt(figures.size)) * Factorial(BigInt(board.fields.size) - BigInt(figures.size)))
//    val withFields = combinationCount * figures.size
//
    val figureCombinations = figures.permutations.toParArray

    val fieldCombinations = Combinations.combs(figures.size, board.fields.toList)

    println("Solving!")
    val t = for {
      figureCombination <- figureCombinations
      fieldCombination <- fieldCombinations
    } yield {

        val c = fieldCombination.zip(figureCombination)
        if (checkFiguresOnBoard(board, c)) {
          Some(c)
        }
        else
          None
      }

    val result = t.flatten
    result.foreach(println)
    println(result.size)
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
