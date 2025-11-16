import scala.io.StdIn.readLine

object TicTacToe {
  def main(args: Array[String]): Unit = {
    new TicTacToe().start()
  }
}

class TicTacToe {

  private val board: Array[Array[String]] =
    Array.fill(3)(Array.fill(3)("*"))

  def start(): Unit = {
    println("Choose X or O: ")
    val p1 = readLine().trim.toUpperCase
    val p2 = if (p1 == "X") "O" else "X"

    printBoard()

    while (true) {

      playerMove(p1, "Player 1")
      if (checkWin(p1)) {
        println("Player 1 wins!")
        return
      }
      if (checkDraw()) {
        println("It's a draw!")
        return
      }

      playerMove(p2, "Player 2")
      if (checkWin(p2)) {
        println("Player 2 wins!")
        return
      }
      if (checkDraw()) {
        println("It's a draw!")
        return
      }
    }
  }

  private def playerMove(symbol: String, player: String): Unit = {
    while (true) {
      println(s"$player: enter row (0–2): ")
      val row = readLine().toIntOption.getOrElse(-1)

      println(s"$player: enter column (0–2): ")
      val col = readLine().toIntOption.getOrElse(-1)

      if (row < 0 || row > 2 || col < 0 || col > 2) {
        println("Invalid move. Try again.")
      } else if (board(row)(col) != "*") {
        println("Spot already taken. Try again.")
      } else {
        board(row)(col) = symbol
        printBoard()
        return
      }
    }
  }

  private def printBoard(): Unit = {
    board.foreach(row => println(row.mkString(" ")))
    println()
  }

  private def checkWin(p: String): Boolean = {
    // rows
    board.exists(row => row.forall(_ == p)) ||
    // columns
    (0 until 3).exists(c => (0 until 3).forall(r => board(r)(c) == p)) ||
    // diagonals
    (0 until 3).forall(i => board(i)(i) == p) ||
    (0 until 3).forall(i => board(i)(2 - i) == p)
  }

  private def checkDraw(): Boolean =
    board.forall(row => row.forall(_ != "*"))
}
