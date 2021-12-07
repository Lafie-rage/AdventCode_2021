class BingoBoard(
    private val board: List<List<Int>>
){
    private val markedValues: MutableList<MutableList<Boolean>> = constructMarkedValues()

    var hasWon: Boolean = false
        private set
    var score: Int = 0
        private set

    fun checkIfContainsValue(value: Int): Boolean {
        board.forEachIndexed { x, column ->
            column.forEachIndexed { y, columnValue ->
                if(columnValue == value) {
                    markedValues[x][y] = true
                    checkIfHasWon()
                    return true
                }
            }
        }
        return false
    }

    private fun checkIfHasWon() {
        // Check if there's a winning column
        markedValues.forEachIndexed { x, column ->
            var count = 0
            column.forEachIndexed { y, isMarked ->
                if(isMarked) count++
            }
            if (count == column.size) {
                hasWon = true
                calculateScore()
            }
        }

        // Check if there's a winning row
        for(y in markedValues.indices) {
            var count = 0
            for(x in markedValues.indices) {
                if(markedValues[x][y]) count++
            }
            if(count == markedValues.size) {
                hasWon = true
                calculateScore()
            }
        }
    }

    private fun calculateScore() {
        println("Winning on board $this")
        var score = 0
        markedValues.forEachIndexed { x, column ->
            column.forEachIndexed { y, isMarked ->
                if(!isMarked) score += board[x][y]
            }
        }
        this.score = score
    }


    private fun constructMarkedValues(): MutableList<MutableList<Boolean>> {
        val result = mutableListOf<MutableList<Boolean>>()
        for (x in board.indices) {
            val column = mutableListOf<Boolean>()
            for (y in board[x].indices) {
                column.add(false)
            }
            result.add(column)
        }
        return result
    }

    override fun toString(): String {
        var result = ""
        result += "Board :\n"
        board.forEach { column ->
            column.forEach {
                result += "$it "
            }
            result += "\n"
        }
        result += "\n"
        return result
    }
}