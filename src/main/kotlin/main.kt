import java.util.*

data class Post(
    val id: Int,
    val fromId: Int,
    val date: Date = Date(),
    val content: String,
    val canDelete: Int,
    val likes: Likes = Likes(),
    val comments: Comments = Comments(),
    val reposts: Int,
    val views: Int,
    val isFavourite: Boolean,
    )

data class Likes(var countLikes: Int = 0)
data class Comments(var countComments: Int = 0)

object WallService {

    private var posts = emptyArray<Post>()
    private var lastId = 0

    fun add(post: Post): Post {
        posts += post.copy(id = ++lastId, likes = post.likes.copy())
        return posts.last()
    }

    fun update(newPost: Post): Boolean {
        for ((index, post) in posts.withIndex()) {
            if (post.id == newPost.id) {
                posts[index] = newPost.copy(likes = post.likes.copy())
                return true
            }
        }
        return false
    }

    fun printPost() {
        for (post in posts) {
            print(post)
            print(" ")
        }
        println()
    }
}
fun main() {
    val likes = Likes(10)
    val comments = Comments(3)
    WallService.add(Post(1, 1, Date(),"Hi", 0, likes, comments, 2, 135, true))
    WallService.add(Post(2, 2, Date(),"Hello", 0, likes, comments, 1, 4, false))
    WallService.update(Post(1, 1, Date(),"How r u", 1, likes, comments, 4, 15, false))
    WallService.printPost()

}