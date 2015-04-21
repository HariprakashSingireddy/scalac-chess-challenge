package chess.solver

import chess.solver.Figures.Figure
import chess.solver.board._

import scala.annotation.tailrec
import scala.collection.parallel.ParSeq

object Solver {

  def apply(board: Board, figures: Seq[Figures.Figure]): ParSeq[Map[Field, Figures.Figure]] = {
    @tailrec
    def exploreBranch(branches: ParSeq[Board], remainingFigures: Seq[Figures.Figure], count: Int): ParSeq[Board] =
      remainingFigures match {
        case Nil => branches
        case figure :: rest =>

          val branched = for {
            b <- branches
            q <- createPossibleBranches(b, figure, count)
          } yield q


          exploreBranch(branched.distinct, rest, count + 1)
      }

    exploreBranch((board :: Nil).par, figures, 1).map(_.figuresOnFields)
  }

  def createPossibleBranches(board: Board, figure: Figure, count: Int): List[Board] = for {
    field: Field <- board.fields
    x <- createBranch(board, figure, field)
    if x.figureCount == count
  } yield x

  def createBranch(board: Board, figure: Figure, field: Field): Option[Board] = {
    def fieldCheck(checkedField: Field) = figure(field, checkedField)

    if (!board.occupied.exists(fieldCheck)) {
      val newField = field -> figure

      Some(Board(fields = board.fields.filterNot(fieldCheck), figuresOnFields = board.figuresOnFields + newField))
    } else
      None
  }
}
