fun main(){
    TicTacToe().start()
}

class TicTacToe {
    private val board = Array(3) { Array(3) {"*"}}

    fun start(){
        println("Choose X or O:")
        val p1 = readLine()?.uppercase() ?: "X"
        val p2 = if (p1 == "X") "O" else "X"

        printBoard()

         while (true) {
            playerMove(p1, "Player 1")

            if (checkWin(p1)) {
                println("Player 1 wins!")
                break
            }
            if (checkDraw()) {
                println("It's a draw!")
                break
            }

            playerMove(p2, "Player 2")

            if (checkWin(p2)) {
                println("Player 2 wins!")
                break
            }
            if (checkDraw()) {
                println("It's a draw!")
                break
            }
    }
    
}

private fun playerMove(symbol: String, playerName: String) {
        while (true) {
            println("$playerName: enter row (0–2): ")
            val row = readLine()?.toIntOrNull()

            println("$playerName: enter column (0–2): ")
            val col = readLine()?.toIntOrNull()

            if (row == null || col == null || row !in 0..2 || col !in 0..2) {
                println("Invalid position. Try again.")
                continue
            }

            if (board[row][col] != "*") {
                println("Spot already taken. Try again.")
                continue
            }

            board[row][col] = symbol
            printBoard()
            break
        }
    }


 private fun printBoard() {
        board.forEach { row ->
            println(row.joinToString(" "))
        }
        println()
    }

    private fun checkWin(p: String): Boolean {
        // rows
        if (board.any { row -> row.all { it == p } }) return true
        // columns
        for (c in 0 until 3)
            if ((0 until 3).all { r -> board[r][c] == p }) return true
        // diagonals
        if ((0 until 3).all { i -> board[i][i] == p }) return true
        if ((0 until 3).all { i -> board[i][2 - i] == p }) return true
        return false
    }

    private fun checkDraw(): Boolean =
        board.all { row -> row.all { it != "*" } }
}
