@file:Suppress("UNUSED_PARAMETER")


import java.io.File
import kotlin.text.*
import java.awt.Point


var character = Point(12, 12)
var input = ""
val maze = inputMaze()


fun inputButton() {
    input = readLine().toString()
    when {
        ((input == "a") || (input == "A")) && (character.x > 0)
                && (maze[character.y, character.x - 1] == " ") -> character.x--

        ((input == "w") || (input == "W")) && (character.y > 0)
                && (maze[character.y - 1, character.x] == " ") -> character.y--

        ((input == "s") || (input == "S")) && (character.y < maze.height)
                && (maze[character.y + 1, character.x] == " ") -> character.y++

        ((input == "d") || (input == "D")) && (character.x < maze.width)
                && (maze[character.y, character.x + 1] == " ") -> character.x++
    }
}

fun inputMaze(): Matrix<String> {
    var outputStream = File("input/labirint.txt").readLines()
    outputStream = outputStream.onEach { it.split("") }
    val stream = createMatrix(outputStream.size, outputStream[0].length, " ")
    for (i in 0 until outputStream.size) {
        for (k in 0 until outputStream[i].length) {
            stream[i, k] = outputStream[i][k].toString()
        }
    }
    return stream
}

fun visibleMaze() {
    var fHeight = 0
    var sHeight = 0
    var fWidth = 0
    var sWidth = 0
    when {
        (character.y - 3 <= 0) && (character.y + 3 < maze.height) -> {
            fHeight = 0
            sHeight = character.y + 3
        }
        (character.y - 3 > 0) && (character.y + 3 < maze.height) -> {
            fHeight = character.y - 3
            sHeight = character.y + 3
        }
        (character.y - 3 > 0) && (character.y + 3 >= maze.height) -> {
            fHeight = character.y - 3
            sHeight = maze.height
        }
    }
    when {
        (character.x - 3 <= 0) && (character.x + 3 < maze.width) -> {
            fWidth = 0
            sWidth = character.x + 3
        }
        (character.x - 3 > 0) && (character.x + 3 < maze.width) -> {
            fWidth = character.x - 3
            sWidth= character.x + 3
        }
        (character.x - 3 > 0) && (character.x + 3 >= maze.width) -> {
            fWidth = character.x - 3
            sWidth = maze.width
        }
    }
    for (i in fHeight until sHeight) {
        for (k in fWidth until sWidth) {
            print(maze[i, k])
        }
        println()
    }
}

fun drawMaze() {
    maze[character.y, character.x] = "¤"
    visibleMaze()
    maze[character.y, character.x] = " "
}

fun redraw() {
    for (i in 1..1000)
        println()
}

fun main() {
    while ((character.x != 0) && (character.y != 0)) {
        drawMaze()
        inputButton()
        redraw()
    }
}