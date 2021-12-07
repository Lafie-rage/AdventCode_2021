import java.io.File

fun day1(isTest: Boolean = false, part: Int = 2) {
    println("DAY 1 :")
    val path = if(isTest) "./src/main/resources/day1.test.txt" else "./src/main/resources/day1.txt"
    when(part) {
        1 -> part1(path)
        2 -> part2(path)
        else -> {
            part1(path)
            part2(path)
        }
    }
}

fun testDay1(part: Int = 2) {
    day1(true)
}

private fun part1(path: String) {
    var count = 0
    var lastValue: Int? = null
    File(path).forEachLine { line ->
        val value: Int = line.toInt()
        lastValue?.let {
            if(value > it) {
                count++
            }
        }
        lastValue = value
    }

    println("Increased $count times\n\n")
}
private fun part2(path: String) {
    var count = 0
    val values = mutableListOf<Int>()

    File(path).forEachLine { line ->
        val value: Int = line.toInt()
        values.add(value)
        if(values.size == 4) {
            if(sumOfCurrentValue(values) > sumOfLastValue(values)) count++
            values.removeAt(0)
        }
    }
    println("Increased $count times\n\n")
}

private fun sumOfCurrentValue(values: List<Int>): Int {
    var sum = 0
    values.forEachIndexed { i, value ->
        if(i > 0)
            sum += value
    }
    return sum
}

private fun sumOfLastValue(values: List<Int>): Int {
    var sum = 0
    values.forEachIndexed { i, value ->
        if(i < (values.size - 1))
            sum += value
    }
    return sum
}