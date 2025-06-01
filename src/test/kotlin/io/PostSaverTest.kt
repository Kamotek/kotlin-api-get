package io

import com.io.PostSaver
import com.model.Post
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.nio.file.Files
import java.nio.file.Path

class PostSaverTest {

 @Test
 fun `savePosts should create files with correct JSON content`(@TempDir tempDir: Path) {
  val folderPath = tempDir.resolve("outputPosts").toString()
  val posts = listOf(
   Post(userId = 1, id = 100, title = "Title1", body = "Body1"),
   Post(userId = 2, id = 200, title = "Title2", body = "Body2")
  )

  PostSaver.savePosts(folderPath, posts)

  val outputDir = tempDir.resolve("outputPosts")
  assertTrue(Files.exists(outputDir), "outputPosts catalogue should exist")

  for (post in posts) {
   val filePath = outputDir.resolve("${post.id}.json")
   assertTrue(Files.exists(filePath), "File ${post.id}.json should exist")

   val content = Files.readString(filePath)

   assertTrue(content.contains("\"userId\": ${post.userId}"), "There's no userId in JSON")
   assertTrue(content.contains("\"id\": ${post.id}"), "There's no id in JSON")
   assertTrue(content.contains("\"title\": \"${post.title}\""), "There's no title in JSON")
   assertTrue(content.contains("\"body\": \"${post.body}\""), "There's no body in JSON")
  }
 }
}
