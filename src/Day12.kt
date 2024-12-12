import java.lang.IndexOutOfBoundsException

fun main() {
    fun checkNeighbours(
        char: Char,
        plot: Pair<Int, Int>,
        garden: MutableList<MutableList<Char>>
    ): Set<Pair<Int, Int>> {
        val neighbours = mutableSetOf<Pair<Int, Int>>()
        plot.neighbours().forEach { n ->
            try {
                if (char == garden[n.second][n.first]) {
                    println("-> plot ${garden[n.second][n.first]} $n is in region")
                    neighbours.add(n)
                } else {
                    println("-> plot ${garden[n.second][n.first]} $n is NOT in region")
                }
            } catch (e: IndexOutOfBoundsException) {
                // do nothing, we're out of garden
            }
        }
        return neighbours
    }

    fun part1(input: List<String>): Int {
        val regions = mutableListOf<Pair<Set<Pair<Int, Int>>, Int>>()
        val allocatedPlots = mutableSetOf<Pair<Int, Int>>()
        val garden = mutableListOf<MutableList<Char>>()
        input.forEachIndexed { y: Int, s: String ->
            garden += mutableListOf<Char>()
            s.forEachIndexed { x: Int, plot: Char ->
                garden[y] += plot
            }
        }
        printCharMatrix(garden)
        garden.forEachIndexed { y: Int, r: MutableList<Char> ->
            r.forEachIndexed { x: Int, plot : Char ->
                if (!allocatedPlots.contains(x to y)) {
                    println("plot ${garden[y][x]} ${x to y} is new region")
                    val region = mutableSetOf<Pair<Int, Int>>()
                    var perimeterSum = 0
                    region.add(x to y)
                    /* Find the region
                       w need to check each time in all four directions as there might be tricky regions like this:
                       ....X..x
                       .x.xxx.x
                       xxxx.xxx
                       where we will first stumble upon 'X', but we need to find all 'x' plots, including those being
                       the same high or to the top related to previously found plots
                     */
                    val newNeighbours = mutableListOf(x to y)
                    println(newNeighbours)
                    while(newNeighbours.isNotEmpty()) {
                        println("> plot ${garden[newNeighbours[0].second][newNeighbours[0].first]} ${newNeighbours[0].first to newNeighbours[0].second} is new region")
                        val neighbours = checkNeighbours(plot, newNeighbours[0], garden)
                        allocatedPlots.add(newNeighbours[0])
                        perimeterSum += (4 - neighbours.size)
                        neighbours.forEach {
                            if (!allocatedPlots.contains(it) && !newNeighbours.contains(it)) {
                                newNeighbours.add(it)
                            }
                        }
                        region.addAll(neighbours)
                        newNeighbours.removeAt(0)
                        println(newNeighbours)
                    }
                    regions.add(Pair(region, perimeterSum))
                } else {
                    println("plot ${garden[y][x]} ${x to y} is already in region")
                }
            }
        }
        var cost = 0
        regions.forEach {r ->
            println(r)
            cost += r.first.size * r.second
        }
        println(cost)
        return cost
    }

    fun findBorderPlots(vector: Pair<Int, Int>, region: Pair<Set<Pair<Int, Int>>, Int>): Set<Pair<Int, Int>> {
        val border = mutableSetOf<Pair<Int, Int>>()
        region.first.forEach {
            if (!region.first.contains(it.first + vector.first to it.second + vector.second)) {
                border.add((it))
            }
        }
        return border
    }

    fun countBorders(border: Set<Pair<Int, Int>>, directions: Pair<Pair<Int, Int>, Pair<Int, Int>>): Int {
        val edges = mutableListOf<List<Pair<Int, Int>>>()
        val allocated = mutableSetOf<Pair<Int, Int>>()
        border.forEach {
            if (it !in allocated) {
                val edge = mutableListOf<Pair<Int, Int>>()
                val plots = mutableListOf(it)
                allocated.add(it)
                while (plots.isNotEmpty()) {
                    val first = plots[0].plus(directions.first)
                    if (border.contains(first) && !allocated.contains(first)) {
                        edge.add(first)
                        plots.add(first)
                        allocated.add(first)
                    }
                    val second = plots[0].plus(directions.second)
                    if (border.contains(second) && !allocated.contains(second)) {
                        edge.add(second)
                        plots.add(second)
                        allocated.add(second)
                    }
                    plots.removeAt(0)
                }
                edges.add(edge)
            }
        }
        return edges.size
    }

    fun part2(input: List<String>): Int {

        val regions = mutableListOf<Pair<Set<Pair<Int, Int>>, Int>>()
        val allocatedPlots = mutableSetOf<Pair<Int, Int>>()
        val garden = mutableListOf<MutableList<Char>>()
        input.forEachIndexed { y: Int, s: String ->
            garden += mutableListOf<Char>()
            s.forEachIndexed { x: Int, plot: Char ->
                garden[y] += plot
            }
        }
        printCharMatrix(garden)
        garden.forEachIndexed { y: Int, r: MutableList<Char> ->
            r.forEachIndexed { x: Int, plot : Char ->
                if (!allocatedPlots.contains(x to y)) {
                    println("plot ${garden[y][x]} ${x to y} is new region")
                    val region = mutableSetOf<Pair<Int, Int>>()
                    var perimeterSum = 0
                    region.add(x to y)
                    /* Find the region
                       w need to check each time in all four directions as there might be tricky regions like this:
                       ....X..x
                       .x.xxx.x
                       xxxx.xxx
                       where we will first stumble upon 'X', but we need to find all 'x' plots, including those being
                       the same high or to the top related to previously found plots
                     */
                    val newNeighbours = mutableListOf(x to y)
                    println(newNeighbours)
                    while(newNeighbours.isNotEmpty()) {
                        println("> plot ${garden[newNeighbours[0].second][newNeighbours[0].first]} ${newNeighbours[0].first to newNeighbours[0].second} is new region")
                        val neighbours = checkNeighbours(plot, newNeighbours[0], garden)
                        allocatedPlots.add(newNeighbours[0])
                        perimeterSum += (4 - neighbours.size)
                        neighbours.forEach {
                            if (!allocatedPlots.contains(it) && !newNeighbours.contains(it)) {
                                newNeighbours.add(it)
                            }
                        }
                        region.addAll(neighbours)
                        newNeighbours.removeAt(0)
                        println(newNeighbours)
                    }
                    regions.add(Pair(region, perimeterSum))
                } else {
                    println("plot ${garden[y][x]} ${x to y} is already in region")
                }
            }
        }
        var cost = 0
        regions.forEach {r ->
            var borders = 0
            val northBorder = findBorderPlots(0 to -1, r)
            borders += countBorders(northBorder, (-1 to 0) to (1 to 0))
            val southBorder = findBorderPlots(0 to 1, r)
            borders += countBorders(southBorder, (-1 to 0) to (1 to 0))
            val westBorder = findBorderPlots(-1 to 0, r)
            borders += countBorders(westBorder, (0 to -1) to (0 to 1))
            val eastBorder = findBorderPlots(1 to 0, r)
            borders += countBorders(eastBorder, (0 to -1) to (0 to 1))
            println("${r.first.size} plots: $r")
            println("north border plots: $northBorder")
            println("south border plots: $southBorder")
            println("west border plots: $westBorder")
            println("east border plots: $eastBorder")
            cost += borders * r.first.size
        }
        return cost
    }

//    // Test if implementation meets criteria from the description, like:
//    check(part1(listOf("test_input")) == 1)

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day12_test")
    check(part1(testInput) == 1930)
    check(part2(testInput) == 1206)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day12")
    part1(input).println()
    part2(input).println()
}
