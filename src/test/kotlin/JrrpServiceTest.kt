import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.vworks.mirai.diceport.service.JrrpService
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class JrrpServiceTest {
    @Test
    fun testJrrp() {
        runBlocking {
            val rpTrial1 = JrrpService.getJrrp(2109346758, 1124096029)
            assertContains(1..100, rpTrial1)
            delay(1000)
            val rpTrial2 = JrrpService.getJrrp(2109346758, 1124096029)
            assertEquals(rpTrial1, rpTrial2)
        }
    }
}