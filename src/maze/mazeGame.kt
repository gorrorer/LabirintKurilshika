@file:Suppress("UNUSED_PARAMETER", "unused")


package maze


fun main() {
    val game = Maze("input/mazeSample.txt")
    while ((game.character.x != 0) && (game.character.y != 0)) {
        game.drawMaze()
        game.inputButton()
    }
}