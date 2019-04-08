@file:Suppress("UNUSED_PARAMETER", "unused")


package maze


import jcurses.system.CharColor
import matrixInterface.*
import java.io.File
import kotlin.text.*
import java.awt.Point
import jcurses.system.Toolkit


class Maze(private val mazeLocation: String) {


    init { if (Regex("""[0-9]+\s[0-9]+\s[0-9]+""").matches(File(mazeLocation).readLines().last()))
    else throw Exception("Wrong game settings") }



    private val characterModel = '¤'
    private val redrawRange = 50
    private val gameSettings = File(mazeLocation).readLines().last().split(" ").map { it.toInt() }
    private val drawDistance =  gameSettings[2]
    private var input = ""
    private val mazeField = inputMaze()
    var character = if ((gameSettings[0] <= mazeField.width) && (gameSettings[1] <= mazeField.height)
        && (mazeField[gameSettings[1], gameSettings[0]] == ' '))
        Point(gameSettings[0], gameSettings[1])
    else throw Exception("Wrong game settings")


    private fun inputMaze(): Matrix<Char> {
        val mazeBuffer = File(mazeLocation).readLines()
        val stream = createMatrix(mazeBuffer.size - 1, mazeBuffer.first().length, ' ')
        for (i in 0 until mazeBuffer.size - 1) {
            for (k in 0 until mazeBuffer[i].length) {
                stream[i, k] = mazeBuffer[i][k]
            }
        }
        return stream
    }


    private fun inputButton() {
        Toolkit.init()
        input = Toolkit.readCharacter().character.toString()
        when {
            ((input.toLowerCase() == "a") && (character.x > 0)
                    && (mazeField[character.y, character.x - 1] == ' ')) -> character.x--

            ((input.toLowerCase() == "w") && (character.y > 0)
                    && (mazeField[character.y - 1, character.x] == ' ')) -> character.y--

            ((input.toLowerCase() == "s") && (character.y < mazeField.height - 1)
                    && (mazeField[character.y + 1, character.x] == ' ')) -> character.y++

            ((input.toLowerCase() == "d") && (character.x < mazeField.width - 1)
                    && (mazeField[character.y, character.x + 1] == ' ')) -> character.x++
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
        Toolkit.clearScreen(CharColor(CharColor.BLACK, CharColor.BLACK))
        mazeField[character.y, character.x] = characterModel
        visibleMaze()
        mazeField[character.y, character.x] = ' '
        inputButton()
    }
}