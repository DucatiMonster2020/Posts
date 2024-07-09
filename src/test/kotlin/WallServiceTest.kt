import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.util.*

class WallServiceTest {

    @Test
    fun add() {
        var lastId = 0
        val result = ++lastId
        assertEquals(1, result)
    }

    @Test
    fun updateExisting() {
        val likes = Likes()
        val comments = Comments()
        WallService.add(Post(1,1, Date(),"Hi",0, likes, comments, 2, 135, true))
        WallService.add(Post(1,2, Date(),"Hello", 0, likes,comments, 4, 120, false))
        WallService.add(Post(2, 1, Date(), "How r u", 0, likes, comments, 5, 2, true))
        val update = Post(1,1, Date(), "Hi", 0, likes, comments, 2, 135, true)
        val result = WallService.update(update)
        assertTrue(result)
    }

    @Test
    fun updateNotExisting() {
        val likes = Likes()
        val comments = Comments()
        WallService.add(Post(0,1, Date(),"Hi",0, likes, comments, 2, 135, true))
        WallService.add(Post(0,1, Date(),"Hello", 0, likes,comments, 4, 120, false))
        WallService.add(Post(0, 1, Date(), "How r u", 0, likes, comments, 5, 2, true))
        val update = Post(0,1, Date(), "Hi", 0, likes, comments, 2, 135, true)
        val result = WallService.update(update)
        assertFalse(result)
    }
}