class Direction(
    val from: Position,
    val to: Position,
) {
    override fun toString(): String {
        return "$from -> $to"
    }

    fun getPositionBetween(): List<Position> {
        val result = mutableListOf<Position>()
        for(x in from.x..to.x) {
            for(y in from.y..to.y) {
                result.add(Position(x, y))
            }
        }
        return result
    }

    fun getLinesBetween(): List<Position> {
        val result = mutableListOf<Position>()
        if(from.x == to.x) {
            if(from.y < to.y) {
                for(y in from.y..to.y) {
                    result.add(Position(from.x, y))
                }
            }
            else {
                for(y in to.y..from.y) {
                    result.add(Position(from.x, y))
                }
            }

        }
        else if (from.y == to.y) {
            if(from.x < to.x) {
                for (x in from.x..to.x) {
                    result.add(Position(x, from.y))
                }
            }
            else {
                for (x in to.x..from.x) {
                    result.add(Position(x, from.y))
                }
            }
        }
        return result
    }
}