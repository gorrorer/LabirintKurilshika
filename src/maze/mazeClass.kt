@file:Suppress("UNUSED_PARAMETER", "unused")


import java.io.File
import kotlin.text.*
import java.awt.Point
import java.lang.IllegalArgumentException
import java.util.Scanner


class Maze(var mazeLocation: String) {



    private val gameSettings = File(mazeLocation).readLines().last().split(" ").map { it.toInt() }
    var character = if (gameSettings.size == 3) Point(gameSettings[0], gameSettings[1] )
                        else throw IllegalArgumentException("Wrong game settings in file")
    private val drawDistance = if ((gameSettings.size == 3) && (gameSettings[2] > 0)) gameSettings[2]
                        else throw IllegalArgumentException("Wrong game settings in file")
    var input = ""
    private val mazeField = inputMaze()


    init { if ((character.x > mazeField.width) || (character.y > mazeField.height)
        || (character.x > 0) || (character.y > 0) || (mazeField[character.y, character.x] != " "))
        throw Exception("Wrong character coordinates") }


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