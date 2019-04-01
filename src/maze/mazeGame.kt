@file:Suppress("UNUSED_PARAMETER", "unused")


package maze


fun main(args: Array<String>) {
    val game = Maze(args.joinToString())
    while ((game.character.x != 0) && (game.character.y != 0)) {
        game.drawMaze()
        game.inputButton()
    }
}