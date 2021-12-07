import java.io.File

fun day2() {
    println("DAY 2 :")
    val position = SubmarinePosition()
    File("./src/main/resources/day2.txt").forEachLine { line ->
        val value  = line.substringAfter(" ").toInt()
        when {
            line.contains("forward") -> {
                position.forward(value)
            }
            line.contains("up") -> {
                position.up(value)
            }
            line.contains("down") -> {
                position.down(value)
            }
            else -> {
                println("Unknown direction : $line")
            }
        }
    }
    println("New $position")
    println("Answer is : ${position.horizontal * position.depth}\n\n")
}