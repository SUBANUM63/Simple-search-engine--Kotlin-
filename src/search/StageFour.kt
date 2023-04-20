package search

import java.io.File

class StageFour {
    fun run(args: Array<String>) {
        val fileName = args[1]
        val noOfPeople = File(fileName).readLines()
        while (true) {
            printMenu()
            when (getMenuSelection()) {
                1 -> findPerson(noOfPeople)
                2 -> printAllPerson(noOfPeople)
                0 -> {
                    println("Bye!")
                    return
                }
                else -> println("Incorrect option! Try again.")
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

    private fun findPerson(noOfPeople: List<String>) {
        println("Enter a name or email to search all suitable people.")
        val query= readln()
        noOfPeople.filter { it.contains(query, true) }.forEach { println(it) }
    }

    private fun getMenuSelection(): Int {
        val userSelection = readln().toInt()
        return if (userSelection in 0..2)
            userSelection
        else 5

    }

}
