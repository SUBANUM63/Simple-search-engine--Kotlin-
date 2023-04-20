package search

class StageTwo {
    fun run() {
        println("Enter the number of people:")
        val noOfPeople = readln().toInt()

        println("Enter all people:")
        val wordLines = List(noOfPeople) { readln() }
        println()
        println("Enter the number of search queries:")
        val noOfQueries = readln().toInt()
        println()
        for (i in 1..noOfQueries) {
            println("Enter data to search people:")
            val data = readln()
            println()
            foundPeople(data, wordLines)

        }

    }

    private fun foundPeople(data: String, wordLines: List<String>) {
        val noOfPeople = mutableListOf<String>()
        for (line in wordLines) {
            if (line.contains(data, true))
                noOfPeople.add(line)
        }
        if (noOfPeople.isEmpty()) {
            println("No matching people found.")
        } else {
            println("People found:")
            noOfPeople.forEach { println(it) }
            println()
        }
    }
}