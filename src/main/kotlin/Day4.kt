import java.io.File

fun day4(isTest: Boolean = false, part: Int = 2) {
    println("DAY 4 :")
    val path = if (isTest) "./src/main/resources/day4.test.txt" else "./src/main/resources/day4.txt"

    when (part) {
        1 -> part1(path)
        2 -> part2(path)
        else -> {
            part1(path)
            part2(path)
        }
    }
}

fun testDay4(part: Int = 2) {
    day4(true, part)
}

private fun part1(path: String) {
    val values = mutableListOf<String>()

    File(path).forEachLine {
        values.add(it)
    }

    // Retrieve bingoValues
    val bingoValues = values[0].split(",").map {
        it.toInt()
    }

    val regexDelimiter = Regex("[ ]+")
    val bingoBoards = mutableListOf<BingoBoard>()
    for(i in 2 until values.size step 6) {
        val bingoBoardValues = mutableListOf<MutableList<Int>>()
        for(y in i until i + 5) {
            val bingoLine = mutableListOf<Int>()
            val valuesInLine = values[y].split(regexDelimiter).filter {
                it.isNotBlank()
            }.map {
                it.toInt()
            }
            valuesInLine.forEachIndexed { x, value ->
                bingoLine.add(value)
            }
            bingoBoardValues.add(bingoLine)
        }
        bingoBoards.add(BingoBoard(bingoBoardValues))
    }

    bingoBoards.forEach {
        println(it)
    }

    bingoValues.forEach { bingoValue ->
        bingoBoards.forEach { board ->
            board.checkIfContainsValue(bingoValue)
            if(board.hasWon) {
                println("Last value checked : $bingoValue")
                println("Score : ${board.score}")
                println("Answer is : ${board.score * bingoValue}")
                return
            }
        }
    }
}

private fun part2(path: String) {
    val values = mutableListOf<String>()

    File(path).forEachLine {
        values.add(it)
    }

    // Retrieve bingoValues
    val bingoValues = values[0].split(",").map {
        it.toInt()
    }

    val regexDelimiter = Regex("[ ]+")
    val bingoBoards = mutableListOf<BingoBoard>()
    for(i in 2 until values.size step 6) {
        val bingoBoardValues = mutableListOf<MutableList<Int>>()
        for(y in i until i + 5) {
            val bingoLine = mutableListOf<Int>()
            val valuesInLine = values[y].split(regexDelimiter).filter {
                it.isNotBlank()
            }.map {
                it.toInt()
            }
            valuesInLine.forEachIndexed { x, value ->
                bingoLine.add(value)
            }
            bingoBoardValues.add(bingoLine)
        }
        bingoBoards.add(BingoBoard(bingoBoardValues))
    }

    bingoBoards.forEach {
        println(it)
    }

    var lastWinningBoard: Pair<BingoBoard?, Int> = Pair(null, 0)
    bingoValues.forEach { bingoValue ->
        val notYetWonBoards = bingoBoards.filter {
            !it.hasWon
        }
        if(notYetWonBoards.isEmpty()) {
            return@forEach
        }
        notYetWonBoards.forEach { board ->
            board.checkIfContainsValue(bingoValue)
            if(board.hasWon) {
                lastWinningBoard = Pair(board, bingoValue)
            }
        }
    }
    println("Last value checked : ${lastWinningBoard.second}")
    println("Score : ${lastWinningBoard.first!!.score}")
    println("Answer is : ${lastWinningBoard.first!!.score * lastWinningBoard.second}")
    return
}
