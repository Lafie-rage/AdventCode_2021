import java.io.File
import kotlin.math.pow

fun day3(isTest: Boolean = false, part: Int = 2) {
    println("DAY 3 :")
    val path = if(isTest) "./src/main/resources/day3.test.txt" else "./src/main/resources/day3.txt"
    when(part) {
        1 -> part1(path)
        2 -> part2(path)
        else -> {
            part1(path)
            part2(path)
        }
    }
}

fun testDay3(part: Int = 2) {
    day3(true)
}

private fun part1(path: String) {
    val occurrences = mutableListOf<IntArray>() // Each item in the list represent the occurrences of 0 or 1 for a bit
    // The IntArray store occurrences this way [occurrence of 0, occurrence of 1]
    File(path).forEachLine {
        it.toCharArray().forEachIndexed { i, bit ->
            if(occurrences.size == i) {
                occurrences.add(IntArray(2))
            }
            if(bit == '1') { // It's a 1
                occurrences[i][1]++
            } else { // It's a 0
                occurrences[i][0]++
            }
        }
    }

    var gamaRate = 0
    occurrences.forEachIndexed { i, occurrence ->
        val mostOccurred = if(occurrence[0] > occurrence[1]) 0 else 1
        gamaRate += mostOccurred * (2.0.pow(occurrences.size - (i+1))).toInt()
    }
    println("Gama rate is : $gamaRate")

    var epsilonRate = 0
    occurrences.forEachIndexed { i, occurrence ->
        val lessOccurred = if(occurrence[0] < occurrence[1]) 0 else 1
        epsilonRate += lessOccurred * (2.0.pow(occurrences.size - (i+1))).toInt()
    }
    println("Epsilon rate is : $epsilonRate")

    println("Answer is : ${gamaRate * epsilonRate}\n\n")
}

private fun part2(path: String) {
    val values = mutableListOf<CharArray>()

    File(path).forEachLine {
        values.add(it.toCharArray())
    }

    val oxygenGeneratorRating = getOxygenGeneratorRating(values.toMutableList())

    val co2ScrubberRating = getCO2ScrubberRating(values.toMutableList())

    println("Answer is : ${oxygenGeneratorRating * co2ScrubberRating }")
}

private fun getOxygenGeneratorRating(values: MutableList<CharArray>): Int {
    val occurrences = mutableListOf<IntArray>() // Each item in the list represent the occurrences of 0 or 1 for a bit
    for(i in 0 until values[0].size) {
        occurrences.add(IntArray(2))
    }
    occurrences.forEachIndexed { i, occurrence ->
        values.forEach {
            val value = it[i]
            if(value == '1') {
                occurrence[1]++
            } else {
                occurrence[0]++
            }
        }
        val mostOccurred = if(occurrence[0] > occurrence[1]) '0' else '1'
        values.removeIf {
            it[i] != mostOccurred
        }
        if(values.size == 1) return@forEachIndexed // Break the foreach if there is only one value left
    }
    var oxygenGeneratorRating = 0
    values[0].forEachIndexed { i, byte ->
        val value = if(byte == '1') 1 else 0
        oxygenGeneratorRating +=  value * (2.0.pow(occurrences.size - (i+1))).toInt()
    }
    println("Oxygen : $oxygenGeneratorRating")
    return oxygenGeneratorRating
}

private fun getCO2ScrubberRating(values: MutableList<CharArray>) : Int {
    val occurrences = mutableListOf<IntArray>() // Each item in the list represent the occurrences of 0 or 1 for a bit
    for(i in 0 until values[0].size) {
        occurrences.add(IntArray(2))
    }
    occurrences.forEachIndexed { i, occurrence ->
        values.forEach {
            val value = it[i]
            if(value == '1') {
                occurrence[1]++
            } else {
                occurrence[0]++
            }
        }
        val lessOccurred = if(occurrence[0] > occurrence[1]) '1' else '0'
        values.removeIf {
            it[i] != lessOccurred
        }
        if(values.size == 1) {
            var co2ScrubberRating = 0
            values[0].forEachIndexed { index, byte ->
                val value = if(byte == '1') 1 else 0
                co2ScrubberRating +=  value * (2.0.pow(occurrences.size - (index+1))).toInt()
            }
            println("CO2 rating : $co2ScrubberRating")
            return co2ScrubberRating// Break the foreach if there is only one value left
        }
    }
    return 0 // Should never happen
}