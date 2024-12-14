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
            freeMap.sortWith(compareBy {it.first})
            while (freeMap[j].first < fileMap[i].first) {
                println("i=$i, j=$j")
                if (fileMap[i].second <= freeMap[j].second) {
                    println("${fileMap[i]} fits into ${freeMap[j]}")
                    val indexCache = fileMap[i].first
                    // Move file
                    fileMap[i] = Triple(freeMap[j].first, fileMap[i].second, fileMap[i].third)
                    // Fix free space
                    if (fileMap[i].second != freeMap[j].second) {
                        val diff = freeMap[j].second - fileMap[i].second
                        freeMap[j] = Triple(freeMap[j].first + fileMap[i].second, diff, -1)
                        println("Put ${Triple(indexCache, fileMap[i].second, fileMap[i].third)} into ${Triple(fileMap[i].first, fileMap[i].second+diff, freeMap[j].third)}; free space now in ${freeMap[j]}, remain $diff")
                        freeMap.add(Triple(indexCache, fileMap[i].second, -1))
                        println("Added free space ${Triple(indexCache, fileMap[i].second, -1)}")
                    } else {
                        freeMap[j] = Triple(indexCache, fileMap[i].second, -1)
                        println("Moved ${Triple(indexCache, fileMap[i].second, fileMap[i].third)} to ${fileMap[i].first}; free space now in ${freeMap[j]}")
                    }
                    // Fix free space
                    var k = 1
                    while (k in 1..freeMap.lastIndex) {
                        if (freeMap[k-1].first + freeMap[k-1].second == freeMap[k].first) {
                            // Merge k and k-1 element
                            print("Merged ${freeMap[k-1]} and ${freeMap[k]} ")
                            freeMap[k-1] = Triple(freeMap[k-1].first ,freeMap[k-1].second + freeMap[k].second ,-1)
                            println("into ${freeMap[k-1]}")
                            freeMap.removeAt(k)
                            k--
                        }
                        k++
                    }
                    break
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
        printDisc(freeMap.toList(), fileMap.toList())
        // calculate checksum
        var checksum : Long = 0
        fileMap.forEach {file ->
            for(i in 0..<file.second) {
                println("index ${file.first + i} file id ${file.third}")
                checksum += (file.first + i) * file.third.toLong()
            }
        }
        println(checksum)
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
