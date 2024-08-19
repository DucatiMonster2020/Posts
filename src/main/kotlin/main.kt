import java.util.*

data class Post(
    val id: Int,
    val fromId: Int,
    val date: Date = Date(),
    val content: String,
    val canDelete: Int,
    val likes: Likes? = null,
    val comments: Comments? = null,
    val reposts: Int,
    val views: Int,
    val isFavourite: Boolean,
    val attachment: Attachment? = null,
    )

data class Likes(var countLikes: Int = 0)
data class Comments(var countComments: Int = 0)

object WallService {

    private var posts = emptyArray<Post>()

    fun clear() {
        posts = emptyArray()
        lastId = 0
    }
    private var lastId = 0

    fun add(post: Post): Post {
        posts += post.copy(id = ++lastId, likes = post.likes?.copy())
        return posts.last()
    }

    fun update(newPost: Post): Boolean {
        for ((index, post) in posts.withIndex()) {
            if (post.id == newPost.id) {
                posts[index] = newPost.copy(likes = post.likes?.copy())
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

interface Attachment {
    val type: String
}
data class Photo(
    val id: Int,
    val owner_id: Int
)
data class PhotoAttachment(val photo: Photo): Attachment {
    override val type: String = "photo"
}
data class Audio(
    val id: Int,
    val duration: Int
)
data class AudioAttachment(val audio: Audio): Attachment {
    override val type: String = "audio"
}
data class Document(
    val id: Int,
    val title: String,
)
data class DocumentAttachment(val document: Document): Attachment {
    override val type: String = "document"
}
data class Video(
    val id: Int,
    val duration: Int
)
data class VideoAttachment(val video: Video): Attachment {
    override val type: String = "video"
}
data class Sticker(
    val id: Int,
    val description: String
)
data class StickerAttachment(val sticker: Sticker): Attachment {
    override val type: String = "sticker"
}
fun main() {
    val likes = Likes(10)
    val comments = Comments(3)
    WallService.add(Post(1, 1, Date(),"Hi", 0, likes, comments, 2, 135, true))
    WallService.add(Post(2, 2, Date(),"Hello", 0, likes, comments, 1, 4, false))
    WallService.update(Post(1, 1, Date(),"How r u", 1, likes, comments, 4, 15, false))
    WallService.printPost()

    val attachment = PhotoAttachment(Photo(1,2))
    println(attachment)

    val array = arrayOf(PhotoAttachment(Photo(1, 2)), AudioAttachment(Audio(1, 330)), DocumentAttachment(Document(1, "Description")), VideoAttachment(Video(1, 100)), StickerAttachment(Sticker(1, "Happy")))
    for (att in array) {
        print(att)
        print(" ")
    }
    println()
}