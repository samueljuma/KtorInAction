# KtorInAction
A simple app showcasing how [Ktor](https://ktor.io/docs/welcome.html) can be leveraged for Network calls. In this project, we build a nice **Comments App**, that fetches [data](https://jsonplaceholder.typicode.com/comments) from a free API Faker **"{JSON} Placeholder"**. 
## What is Ktor?
A lightweight and flexible framework for building asynchronous client and server-side applications in Kotlin
## Why Ktor? 
- **Kotlin and Coroutines** - Ktor is built from the ground up using Kotlin and Coroutines, as such it is provides a "Kotlin-first" approach and support for asynchronous tasks. 
- **Lightweight and flexible** - Ktor allows you to only use what you need and to structure your application the way you need. 
- **Built and backed by JetBrains** - the very creator of Kotlin, IntelliJ IDEA and more 
- **Multiplatform support** - Ktor is a multiplatform library, allowing you to share network code between different platforms (Android, iOS, JVM, JS, Native).

# Let's Build
While getting data from the internet using **Ktor** is the primary focus for this project, we will use other libraries including
- Koin - for dependency injection
- Kotlinx-serialization - core library for serializing and deserializing Kotlin objects
- kotlinx-serialization-json - provides JSON Format specific support . For entity serialization
- Kotlinx-coroutines 
- Content-negotiation - needed for Ktor http requests and kotlinx-seialization to work together seamlessly 
- lifecycle 
- Logback - a logging framework that enables logging for Ktor
 
## Step 1: Add Dependencies and Plugins 
### We use versions Catalog
- Add the following in ```libs.versions.toml``` file 
```
[versions]
kotlin = "2.0.0"
ktor = "2.3.12"
kotlinx-serialization = "2.0.0"
kotlinx-serialization-json = "1.7.1"
logbackClassic = "1.3.12" 
lifecycle = "2.8.4"
koin = "3.5.6"
koinCompose = "3.4.6"
kotlinxCoroutines = "1.9.0-RC"
```
```
[libraries]
ktor-client-core = { module = "io.ktor:ktor-client-core", version.ref = "ktor" } # core engine. Not necessary
ktor-client-android = { module = "io.ktor:ktor-client-android", version.ref = "ktor"} # engine that handles network requests on Android
kotlinx-serialization-json = { module = "org.jetbrains.kotlinx:kotlinx-serialization-json", version.ref = "kotlinx-serialization-json"}
ktor-client-content-negotiation = { module = "io.ktor:ktor-client-content-negotiation", version.ref = "ktor" }
ktor-serialization-kotlinx-json = { module = "io.ktor:ktor-serialization-kotlinx-json", version.ref = "ktor" }
ktor-client-logging = {module = "io.ktor:ktor-client-logging", version.ref = "ktor"}
logback-classic = { module = "ch.qos.logback:logback-classic", version.ref = "logbackClassic" }
lifecycle-viewmodel-compose = { module = "androidx.lifecycle:lifecycle-viewmodel-compose", version.ref = "lifecycle" }
compose-lifecycle = { module = "androidx.lifecycle:lifecycle-runtime-compose", version.ref = "lifecycle"}
#dependency injection
koin-core = { module ="io.insert-koin:koin-core", version.ref = "koin"}
koin-android = { module = "io.insert-koin:koin-android", version.ref = "koin"}
koin-androidx-compose = { module = "io.insert-koin:koin-androidx-compose", version.ref = "koinCompose"}
```
```
[Plugins]
kotlinxSerialization = { id = "org.jetbrains.kotlin.plugin.serialization", version.ref = "kotlinx-serialization" }
compose-compiler = { id = "org.jetbrains.kotlin.plugin.compose", version.ref = "kotlin" }
```
```
# Note: Bundles help us provide related dependencies all at once.
[bundles]
koin = ["koin-core", "koin-android", "koin-androidx-compose"]
ktor = ["ktor-client-core", "ktor-client-android", "kotlinx-serialization-json", "ktor-client-content-negotiation",
    "ktor-serialization-kotlinx-json","ktor-client-logging", "logback-classic"]
coroutines = ["kotlinx-coroutines-core", "kotlinx-coroutines-android"]
```
