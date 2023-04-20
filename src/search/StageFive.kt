package search

import java.io.File

class StageFive {
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


    private fun buildInvertedIndex(lines: List<String>): Map<String, List<Int>> {
        val invertedIndex = mutableMapOf<String, MutableList<Int>>()
        for (line in lines.withIndex()) {
            val words = line.value.split(" ")
            for (word in words) {
                val list = invertedIndex.getOrDefault(word, mutableListOf())
                list.add(line.index)
                invertedIndex[word] = list
            }
        }
        return invertedIndex
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

    private fun findPerson(foundPeople: List<String>, invertedIndex:Map<String, List<Int>> ) {
        println("Enter a name or email to search all suitable people.")
        val query= readln()
        if (!invertedIndex.containsKey(query)) {
            println("No matching people found.")
        } else {
            val listOfMatches = invertedIndex[query]
            println("${listOfMatches?.size} person found:")
            listOfMatches?.forEach { println(foundPeople[it]) }
        }
    }

    private fun getMenuSelection(): Int {
        val userSelection = readln().toInt()
        return if (userSelection in 0..2)
            userSelection
        else 5

    }

}