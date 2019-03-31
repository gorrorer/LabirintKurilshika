@file:Suppress("UNUSED_PARAMETER", "unused")


package maze


import matrixInterface.*
import java.io.File
import kotlin.text.*
import java.awt.Point
import java.util.Scanner



class Maze(var mazeLocation: String) {


    init { if (Regex("""[0-9]+\s[0-9]+\s[0-9]+""").matches(File(mazeLocation).readLines().last()))
    else throw Exception("Wrong game settings") }


    private val gameSettings = File(mazeLocation).readLines().last().split(" ").map { it.toInt() }
    private val drawDistance =  gameSettings[2]
    var input = ""
    private val mazeField = inputMaze()
    var character = if ((gameSettings[0] <= mazeField.width) && (gameSettings[1] <= mazeField.height)
        && (mazeField[gameSettings[1], gameSettings[0]] == " "))
        Point(gameSettings[0], gameSettings[1])
    else throw Exception("Wrong game settings")


    private fun inputMaze(): Matrix<String> {
        val outputStream = File(mazeLocation).readLines()
        val stream = createMatrix(outputStream.size - 1, outputStream.first().length, " ")
        for (i in 0 until outputStream.size - 1) {
            for (k in 0 until outputStream[i].length) {
                stream[i, k] = outputStream[i][k].toString()
            }
        }
        return stream
    }


    fun inputButton() {
        input = Scanner(System.`in`).next()     //here i want to realize input without pressing "Enter"
        when {
            ((input.toLowerCase() == "a") && (character.x > 0)
                    && (mazeField[character.y, character.x - 1] == " ")) -> character.x--

            ((input.toLowerCase() == "w") && (character.y > 0)
                    && (mazeField[character.y - 1, character.x] == " ")) -> character.y--

            ((input.toLowerCase() == "s") && (character.y < mazeField.height - 1)
                    && (mazeField[character.y + 1, character.x] == " ")) -> character.y++

            ((input.toLowerCase() == "d") && (character.x < mazeField.width - 1)
                    && (mazeField[character.y, character.x + 1] == " ")) -> character.x++
        }
    }


    private fun visibleMaze() {
        val fHeight = maxOf(character.y - drawDistance, 0)
        val sHeight = minOf(character.y + drawDistance, mazeField.height - 1)
        val fWidth = maxOf(character.x - drawDistance, 0)
        val sWidth = minOf(character.x + drawDistance, mazeField.width - 1)
        for (i in fHeight..sHeight) {
            for (k in fWidth..sWidth) {
                print(mazeField[i, k])
            }
            println()
        }
    }


    fun drawMaze() {
        for (i in 1..50)
            println()
        mazeField[character.y, character.x] = "¤"
        visibleMaze()
        mazeField[character.y, character.x] = " "
    }
}