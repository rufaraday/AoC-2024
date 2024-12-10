fun main() {
    fun part1(input: List<String>): Long {
        val disc = emptyList<Int>().toMutableList()
        // read disc
        var id = 0
        for(i in 0..input[0].lastIndex) {
            for(j in 0..<input[0][i].digitToInt()) {
                if (i.rem(2) == 0) {
                    disc.add(id)
                } else {
                    disc.add(-1)
                }
            }
            if (i.rem(2) == 0) {
                id++
            }
        }
//        println(disc)
        // move files
        for (i in 0..disc.lastIndex) {
            if (disc[i] >= 0) continue
            for (j in disc.lastIndex downTo i) {
                if (disc[j] >= 0) {
                    disc[i] = disc[j]
                    disc[j] = -1
                    break
                }
            }
//            println(disc)
        }
        // calculate checksum
        var checksum : Long = 0
        for (i in 0..disc.lastIndex) {
            if (disc[i] > 0) {
                checksum += i * disc[i]
            }
        }
        return checksum
    }

    fun printDisc(free: List<Triple<Int, Int, Int>>, files: List<Triple<Int, Int, Int>>) {
        val discElements = (free + files).toMutableList()
        val disc = mutableListOf<Int>()
        discElements.sortWith(compareBy {it.first})
        var index = 0
        discElements.forEach() {
            if (it.first == index) {
                for (i in 0..<it.second) {
                    disc.add(it.third)
                }
            } else {
                println("error writing $it to $disc")
                throw RuntimeException()
            }
            index += it.second
        }
        println("disc: $disc")
    }

    fun part2(input: List<String>): Long {
        val disc = emptyList<Int>().toMutableList()
        // read disc
        val freeMap = mutableListOf<Triple<Int, Int, Int>>()
        val fileMap = mutableListOf<Triple<Int, Int, Int>>()
        var discIndex = 0
        for(i in 0..input[0].lastIndex) {
            if (i.rem(2) == 0) {
                fileMap.add(Triple(discIndex, input[0][i].digitToInt(), i.div(2)))
            } else {
                freeMap.add(Triple(discIndex, input[0][i].digitToInt(), -1))
            }
            discIndex += input[0][i].digitToInt()
        }
        println("free=$freeMap")
        println("file=$fileMap")
//        println(disc)
        // defrag move files
        for (i in fileMap.size-1 downTo 1) {
            var j = 0
            while (j < freeMap.size) {
                println("i=$i, j=$j")
                if (fileMap[i].second <= freeMap[j].second) {
                    println("${fileMap[i]} fits into ${freeMap[j]}")
                    fileMap[i] = Triple(freeMap[j].first, fileMap[i].second, fileMap[i].third)
                    if (fileMap[i].second != freeMap[j].second) {
                        val diff = freeMap[j].second - fileMap[i].second
                        freeMap[j] = Triple(freeMap[j].first + fileMap[i].second, diff, -1)
                        println("Put ${fileMap[i]} into ${freeMap[j]}, remain $diff")
                        break
                    } else {
                        println("remove ${freeMap[j]}")
                        freeMap.removeAt(j)
                        break
                    }
                }
                j++
                println("free=$freeMap")
                println("file=$fileMap")
                printDisc(freeMap.toList(), fileMap.toList())
//                Thread.sleep(1000)
            }
        }
        println("free=$freeMap")
        fileMap.sortWith(compareBy {it.first})
        println("sorted files=$fileMap")
        // calculate checksum
        var checksum : Long = 0
        for (i in 0..disc.lastIndex) {
            if (disc[i] > 0) {
                checksum += i * disc[i]
            }
        }
        return checksum
    }

    // Test if implementation meets criteria from the description, like:
//    check(part1(listOf("test_input")) == 1)

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day09_test")
    check(part1(testInput) == 1928.toLong())
    check(part2(testInput) == 2858.toLong())

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day09")
    part1(input).println()
    part2(input).println()
}
