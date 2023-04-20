package search

class StageOne {
    fun run() {
        var indexOfWord = 0
        val multiWord = readln()
        val searchWord = readln()
        val multiWordList = multiWord.split(' ').toList()
        for (i in multiWordList.indices) {
            if (searchWord == multiWordList[i]) {
                indexOfWord = i + 1
                break
            }
        }
        println(if (indexOfWord != 0) indexOfWord else "Not Found")
    }
}
/*

// ANOTHER POSSIBLE SOLUTION
fun main() = readLine()!!
    .split(" ")
    .indexOf(readLine()!!)
    .let { if (it < 0) "Not found" else it + 1 }
    .let(::println)*/
