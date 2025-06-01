package io

import com.io.PostSerializer
import com.model.Post
import org.junit.jupiter.api.Test
import kotlinx.serialization.json.*
import kotlin.test.assertContains
import kotlin.test.assertEquals


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


  assertContains(jsonElement, "userId",message = "There's no userId in JSON")
  assertContains(jsonElement, "id", message = "Ther's no id in JSON")
  assertContains(jsonElement,"title", message = "There's no title in JSON")
  assertContains(jsonElement,"body", message = "There's no body in JSON")


  assertEquals(69, jsonElement["userId"]!!.jsonPrimitive.int, "Invalid UserId value")
  assertEquals(3, jsonElement["id"]!!.jsonPrimitive.int, "Invalid id value")
  assertEquals(
   "Title test 123",
   jsonElement["title"]!!.jsonPrimitive.content,
   "Invalid title value"
  )
  assertEquals(
   "Body test 123",
   jsonElement["body"]!!.jsonPrimitive.content,
   "Invalid Body test value"
  )

 }
}
