package io

import com.io.PostSaver
import com.model.Post
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.nio.file.Files
import java.nio.file.Path
import kotlin.test.assertContains

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

   assertContains(content, "\"userId\": ${post.userId}", message = "There's no userId in JSON")
   assertContains(content, "\"id\": ${post.id}", message = "There's no id in JSON")
   assertContains(content, "\"title\": \"${post.title}\"", message = "There's no title in JSON")
   assertContains(content, "\"body\": \"${post.body}\"", message = "There's no body in JSON")
  }
 }
}
