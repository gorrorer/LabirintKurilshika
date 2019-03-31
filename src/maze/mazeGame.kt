@file:Suppress("UNUSED_PARAMETER", "unused")


fun main() {
    val game = Maze("input/labirint.txt")
    while ((game.character.x != 0) && (game.character.y != 0)) {
        game.drawMaze()
        game.inputButton()
    }
}