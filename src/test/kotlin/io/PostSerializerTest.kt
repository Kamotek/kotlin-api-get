package io

import com.io.PostSerializer
import com.model.Post
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlinx.serialization.json.*

class PostSerializerTest {

 @Test
 fun `toJson should produce valid JSON with all fields`() {
  val post = Post(
   userId = 69,
   id = 3,
   title = "Title test 123",
   body = "Body test 123"
  )

  val jsonString = PostSerializer.toJson(post)

  val jsonElement = Json.decodeFromString<JsonObject>(jsonString)

  assertTrue(jsonElement.containsKey("userId"), "There's no userId in JSON")
  assertTrue(jsonElement.containsKey("id"), "Ther's no id in JSON")
  assertTrue(jsonElement.containsKey("title"), "There's no title in JSON")
  assertTrue(jsonElement.containsKey("body"), "There's no body in JSON")

  assertTrue(jsonElement["userId"]!!.jsonPrimitive.int == 69, "Invalid UserId value")
  assertTrue(jsonElement["id"]!!.jsonPrimitive.int == 3, "Invalid id value")
  assertTrue(jsonElement["title"]!!.jsonPrimitive.content == "Title test 123", "Invalid title value")
  assertTrue(jsonElement["body"]!!.jsonPrimitive.content == "Body test 123", "Invalid Body test value")
 }
}
