@file:Suppress("UNUSED_PARAMETER", "unused")


import java.io.File
import kotlin.text.*
import java.awt.Point
import java.util.Scanner


class Maze(var mazeLocation: String) {


    var character = Point(12, 12)
    var input = ""
    private val mazeField = inputMaze()
    private val drawDistance = 3


    private fun inputMaze(): Matrix<String> {
        val outputStream = File(mazeLocation).readLines()
        val stream = createMatrix(outputStream.size, outputStream.first().length, " ")
        for (i in 0 until outputStream.size) {
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

            ((input.toLowerCase() == "s") && (character.y < mazeField.height)
                    && (mazeField[character.y + 1, character.x] == " ")) -> character.y++

            ((input.toLowerCase() == "d") && (character.x < mazeField.width)
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
        mazeField[character.y, character.x] = "¤"
        visibleMaze()
        mazeField[character.y, character.x] = " "
    }


    fun redraw() {
        for (i in 1..50)
            println()
    }
}