package network

import com.model.Post
import com.network.PostApiClient
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.*

class PostApiClientTest {

 @Test
 fun `getPosts returns valid list from proper source`() = runBlocking {
  val validUrl = "https://jsonplaceholder.typicode.com/posts"

  val posts: List<Post> = PostApiClient.getPosts(validUrl)

  assertTrue(posts.isNotEmpty(), "non-empty array of posts")

  val post: Post = posts.first()
  assertTrue(post.id is Int, "id should be of type Int")
  assertTrue(post.userId is Int, "userId should be of type Int")
  assertTrue(post.title.isNotBlank(), "title should not be blank")
  assertTrue(post.body.isNotBlank(), "content sholud not be blank")
 }

 @Test
 fun `getPosts returns empty list if there's a problem with endpoint connection`() = runBlocking {

  val invalidUrl = "https://jsonplaceholder.typicode.com/invalid-endpoint"

  val posts: List<Post> = PostApiClient.getPosts(invalidUrl)

  assertTrue(posts.isEmpty(), "Empty list expected")
 }
}
