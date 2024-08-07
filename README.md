# KtorInAction
A simple app showcasing how [Ktor](https://ktor.io/docs/welcome.html) can be leveraged for Network calls. In this project, we build a nice **Comments App**, that fetches [data](https://jsonplaceholder.typicode.com/comments) from a free API Faker **"{JSON} Placeholder"**. 
## What is Ktor?
A lightweight and flexible framework for building asynchronous client and server-side applications in Kotlin
## Why Ktor? 
- **Kotlin and Coroutines** - Ktor is built from the ground up using Kotlin and Coroutines, as such it is provides a "Kotlin-first" approach and support for asynchronous tasks. 
- **Lightweight and flexible** - Ktor allows you to only use what you need and to structure your application the way you need. 
- **Built and backed by JetBrains** - the very creator of Kotlin, IntelliJ IDEA and more 
- **Multiplatform support** - Ktor is a multiplatform library, allowing you to share network code between different platforms (Android, iOS, JVM, JS, Native).

# Let's Build 👷‍♂️⚒️🪛
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
```toml
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
```toml
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
```toml
[Plugins]
kotlinxSerialization = { id = "org.jetbrains.kotlin.plugin.serialization", version.ref = "kotlinx-serialization" }
compose-compiler = { id = "org.jetbrains.kotlin.plugin.compose", version.ref = "kotlin" }
```
```toml
# Note: Bundles help us provide related dependencies all at once.
[bundles]
koin = ["koin-core", "koin-android", "koin-androidx-compose"]
ktor = ["ktor-client-core", "ktor-client-android", "kotlinx-serialization-json", "ktor-client-content-negotiation",
    "ktor-serialization-kotlinx-json","ktor-client-logging", "logback-classic"]
coroutines = ["kotlinx-coroutines-core", "kotlinx-coroutines-android"]
```
- Add the following in ```build.gradle.kts``` project-level file
```agsl
plugins {
    alias(libs.plugins.kotlinxSerialization) apply false
    alias(libs.plugins.compose.compiler) apply false
}
```
- Add the following in ```build.gradle.kts``` app-level file
```kotlin
plugins {
    alias(libs.plugins.kotlinxSerialization)
    alias(libs.plugins.compose.compiler)
}

dependencies {
    implementation(libs.bundles.ktor)
    implementation(libs.lifecycle.viewmodel.compose)
    implementation(libs.compose.lifecycle)
    implementation(libs.bundles.koin)
    implementation(libs.bundles.coroutines)
}
```
- Be sure to ```sync``` your project after adding dependencies. 
While at it you're likely to run into an ```issue``` like this one here⬇️
#### Possible Issues❗
> x files found with path 'META-INF/INDEX.LIST' . Adding a packaging block may help, please refer to https://developer.android.com/reference/tools/gradle-api/8.5/com/android/build/api/dsl/Packaging for more information

#### Solution
- Add the line ```excludes +="META-INF/INDEX.LIST"``` to the packaging block in your ```build.gradle.kts``` app-level file

```kotlin
android {
    // ... Rest of the code
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes +="META-INF/INDEX.LIST" // Add this line
        }
    }
}
```