package chess.solver


object Timer {
  def apply[R](block: => R): R = {
    val t0 = System.nanoTime()
    val result = block // call-by-name
    val t1 = System.nanoTime()

    println(s"Elapsed time: ${(t1 - t0) / 1000000000.0} s")
    result
  }

  def apply[R](str: String)(block: => R): R = {
    val t0 = System.nanoTime()
    println(s"Starting $str")
    val result = block // call-by-name
    val t1 = System.nanoTime()

    println(s"$str Elapsed time: ${(t1 - t0) / 1000000000.0} s")
    result
  }
}
