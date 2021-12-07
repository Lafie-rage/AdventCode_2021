class SubmarinePosition {
    var horizontal = 0
        private set
    var depth = 0
        private set
    var aim = 0
        private set

    fun up(value: Int) {
        // Part 1
        // depth -= value
        // Part 2
        aim -= value
    }

    fun down(value: Int) {
        // Part 1
        // depth += value
        // Part 2
        aim += value
    }

    fun forward(value: Int) {
        horizontal += value
        // Part 2
        depth += aim * value
    }

    override fun toString(): String {
        return "Position: {\n" +
                "\thorizontal : $horizontal, \n" +
                "\tdepth : $depth\n" +
                "}"
    }
}