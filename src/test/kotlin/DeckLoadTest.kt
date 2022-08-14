import org.vworks.mirai.diceport.data.Decks
import java.io.File
import kotlin.test.Test
import kotlin.test.assertTrue

class DeckLoadTest {
    @Test
    fun testEqualChanceDeck() {
        val file = File("src/test/resources/test-tarot.yml")
        assertTrue(file.exists())

        Decks.loadSingleDeck(file, file.readText())

        assertTrue(Decks.decks.containsKey("单张塔罗牌"))
    }
}