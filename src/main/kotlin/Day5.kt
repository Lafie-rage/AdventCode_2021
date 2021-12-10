import java.io.File

fun day5(isTest: Boolean = false, part: Int = 2) {
    println("DAY 4 :")
    val path = if (isTest) "./src/main/resources/day5.test.txt" else "./src/main/resources/day5.txt"

    when (part) {
        1 -> part1(path)
        2 -> part2(path)
        else -> {
            part1(path)
            part2(path)
        }
    }
}

fun testDay5(part: Int = 2) {
    day5(true, part)
}

private fun part1(path: String) {
    val values = mutableListOf<String>()

    File(path).forEachLine {
        values.add(it)
    }

    val directions = mutableListOf<Direction>()
    values.forEach { value ->
        val fromTo = value.split(" -> ")
        val from = fromTo[0].split(",").map { it.toInt() }
        val to = fromTo[1].split(",").map { it.toInt() }
        directions.add(
            Direction(
                Position(from[0], from[1]),
                Position(to[0], to[1]),
            )
        )
    }

    val positionsOnAnyDirection = directions.flatMap {
        it.getLinesBetween()
    }

    val allPositionsOccurrences = positionsOnAnyDirection
        .groupBy { it }
        .map {
            PositionOccurrence(it.value.size, it.value[0])
        }

    val positionThatMostOccurred = allPositionsOccurrences.filter { it.occurrence > 1 }


    println("Answer is ${positionThatMostOccurred.size}")
}

private fun part2(path: String) {
    val values = mutableListOf<String>()

    File(path).forEachLine {
        values.add(it)
    }

    val directions = mutableListOf<Direction>()
    values.forEach { value ->
        val fromTo = value.split(" -> ")
        val from = fromTo[0].split(",").map { it.toInt() }
        val to = fromTo[1].split(",").map { it.toInt() }
        directions.add(
            Direction(
                Position(from[0], from[1]),
                Position(to[0], to[1]),
            )
        )
    }

    val positionsOnAnyDirection = directions.flatMap {
        it.getPositionBetween()
    }


    val allPositionsOccurrences = positionsOnAnyDirection
        .groupBy { it }
        .map {
            PositionOccurrence(it.value.size, it.value[0])
        }

    for (x in 0 until 9) {
        for (y in 0 until 9) {
            val positionOccurrences = allPositionsOccurrences.filter { it.position.x == x && it.position.y == y }
            val occurrence: String =
                if (positionOccurrences.isNotEmpty()) positionOccurrences[0].occurrence.toString() else "."
            print(occurrence)
        }
        println()
    }

//    val positionThatMostOccurred = allPositionsOccurrences.filter { it.occurrence > 1 }


//    println("Answer is ${positionThatMostOccurred.size}") // Should be 12
}
