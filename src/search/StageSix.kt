package search

import java.io.File

class StageSix {

    fun run(args: Array<String>) {
        val fileName = args[1]
        val allPeople = File(fileName).readLines()
        val invertedIndex = buildInvertedIndex(allPeople)
        while (true) {
            printMenu()
            when (getMenuSelection()) {
                1 -> findPerson(allPeople, invertedIndex)
                2 -> printAllPerson(allPeople)
                0 -> {
                    println("Bye!")
                    return
                }
                else -> println("Incorrect option! Try again.")
            }
        }

    }


    private fun buildInvertedIndex(lines: List<String>): Map<String, Set<Int>> {
        val invertedIndex = mutableMapOf<String, MutableSet<Int>>()
        for (line in lines.withIndex()) {
            val words = line.value.split(" ")
            for (word in words) {
                val list = invertedIndex.getOrDefault(word.lowercase(), mutableSetOf())
                list.add(line.index)
                invertedIndex[word.lowercase()] = list
            }
        }
        return invertedIndex
    }


    private fun findPerson(foundPeople: List<String>, invertedIndex: Map<String, Set<Int>>) {
        val strategy = getSearchingStrategy()

        println("Enter a name or email to search all suitable people.")
        val queryParts = readLine()!!.split(" ")

        val matchesInIndex = when (strategy) {
            "ALL" -> {
                queryParts.map { getFromIndex(invertedIndex, it) }
                    .reduce { acc, set -> acc.intersect(set).toMutableSet() }
            }

            "ANY" -> {
                queryParts.map { getFromIndex(invertedIndex, it) }.reduce { acc, set -> acc.union(set).toMutableSet() }
            }

            "NONE" -> {
                var allLines = foundPeople.indices.toMutableSet()
                for (query in queryParts) {
                    allLines = allLines.minus(getFromIndex(invertedIndex, query)).toMutableSet()
                }
                allLines
            }

            else -> emptySet()
        }

        if (matchesInIndex.isNotEmpty()) {
            println("${matchesInIndex.size} person found:")
            matchesInIndex.forEach { println(foundPeople[it]) }
        } else {
            println("No matching people found.")
        }
    }

    private fun getFromIndex(index: Map<String, Set<Int>>, query: String): Set<Int> {
        return index[query.lowercase()] ?: emptySet()
    }

    private fun getSearchingStrategy(): String {
        while (true) {
            println("Select a matching strategy: ALL, ANY, NONE")

            val strategy = readLine()!!
            if (strategy in listOf("ALL", "ANY", "NONE")) {
                return strategy
            }
        }
    }

    private fun printMenu() {
        println()
        println("=== Menu ===")
        println("1. Find a person")
        println("2. Print all people")
        println("0. Exit")
        println()
    }

    private fun printAllPerson(noOfPeople: List<String>) {
        println("=== List of people ===")
        noOfPeople.forEach { println(it) }
    }

    private fun getMenuSelection(): Int {
        val userSelection = readln().toInt()
        return if (userSelection in 0..2)
            userSelection
        else 5

    }

}