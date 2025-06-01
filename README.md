# Kotlin API fetcher

I'd love you to share a little bit about my application:

It's purpose is a demonstration on how to use Ktor Client to fetch data from public REST API endpoint.

Propositions on how to run it:

## 1. Use your IDE (ex. InteliJ Idea), Run MainKt with Java17
   - The application will ask you to specify dirname for our posts.
## 2. Create jar file and run it:
>./gradlew shadowJar && sudo sudo java -jar build/libs/kotlin_task_project-1.0-SNAPSHOT-all.jar
   - same thing, application will ask you to specify dirname, and will create this directory and save there our posts
## 3. Build your docker image:
>docker build -t my-kotlin-app .

Then make a folder

> mdkir posts && chmod 777 posts

Then run this command
> echo "posts" | docker run --rm -i -v $(pwd)/posts:/app/posts my-kotlin-app:latest



## Stack:
- Java JDK-17 
- Kotlin 2.0.21
- Docker
- Git & GitHub Actions

## Libraries:

- kotlinx-serialization - deserializes data while fetching from API, and serializes data classes while saving it to file
- Logback - used to print potential errors on stderr
- JUnit 5 - used to cover our application in simple tests
- Ktor Client Mock - helps mock http responses while testing
- Gradle + Shadow Plugin - used to build and pack application into runnable fat jar




## Project structure:
- com.config - holds static configuration
- com.model - model layer, contains serializable data class Post
- com.network - PostApiClient handles communication with remote API, also deserializing initial response.body
- com.io - serializes Post data classes and saves them as JSON locally
- com (root) - entry point, handles very simple input handling, and ties everything together
- resources/logback.xml - controls logging format and logback levels
- test packages
  - io - handles very simple tests for PostSaver and PostSerializer classes
  - network - handles very simple tests for PostApiClient class