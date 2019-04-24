@file:Suppress("UNUSED_PARAMETER", "unused")


package maze


fun main(args: Array<String>) {
    val location: String
    var model = '¤'
    val input = args.joinToString().split(" ")
    if (input.size == 2){
        location = input[0]
        model = input[1].last() }
    else location = input.first()
    val game = Maze(location)
    game.characterModel = model
    while ((game.character.x != 0) && (game.character.y != 0)) {
        game.drawMaze()
    }
}