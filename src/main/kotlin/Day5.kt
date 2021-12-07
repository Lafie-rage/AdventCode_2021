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

    directions.forEach { direction ->
        println("For direction $direction")
        direction.getLinesBetween().forEach {
            print("$it ")
        }
        println()
    }
}

private fun part2(path: String) {

}
