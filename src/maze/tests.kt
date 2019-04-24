package maze

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import java.awt.Point

class Tests {

    var gameTest = Maze("input/mazeSample.txt")

    @Test
    fun visibleMaze1() {
        assertEquals(gameTest.visibleMaze(), "═╝ ║   \n" + "   ╩═══\n" + " ════╗ \n" + "     ║ \n" + "═════╝ " + "\n")
    }

    @Test
    fun visibleMaze2() {
        gameTest.character = Point(7, 8)
        assertEquals(gameTest.visibleMaze(), "╝     ║\n" + "  ══╗ ║\n" + "╔═══╝ ║\n" + "║     ║\n" + "╚═╗ ╔═╝\n"
                + "  ║ ║  \n" + "╗ ║ ╚ ═" + "\n")
    }
}