plugins {
    kotlin("jvm") version "1.9.23"
    // Plugin para la generación de código SQLDelight
    id("app.cash.sqldelight") version "2.0.2"
    // Plugin de serielización
    kotlin("plugin.serialization") version "1.9.23"
    // Plugin de Koin KSP
    id("com.google.devtools.ksp") version "1.9.23-1.0.20" //"1.8.21-1.0.11"
    application
    id("org.jetbrains.dokka") version "1.9.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
}

dependencies {
    // Default
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    //Mockk
    testImplementation("io.mockk:mockk:1.13.10")
    // Koin
    testImplementation("io.insert-koin:koin-test-junit5") // Para test con JUnit5

    // SQLDelight para SQLite
    implementation("app.cash.sqldelight:sqlite-driver:2.0.2")
    //Mordant
    implementation("com.github.ajalt.mordant:mordant:2.3.0")
    implementation("net.java.dev.jna:jna:5.13.0")
    //Logger
    implementation("org.lighthousegames:logging:1.3.0")
    implementation("ch.qos.logback:logback-classic:1.4.14")
    //Serialization JSON
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
    //BBDD
    implementation("org.xerial:sqlite-jdbc:3.45.2.0")
    //ScriptRunner
    implementation("org.mybatis:mybatis:3.5.13")
    // Result ROP
    implementation("com.michael-bull.kotlin-result:kotlin-result:2.0.0")
    //Koin
    implementation(platform("io.insert-koin:koin-bom:3.5.3"))
    implementation("io.insert-koin:koin-core") // Core
    implementation("io.insert-koin:koin-test") // Para test y usar checkModules
    // Koin DI
    // Koin, con BOM ya se instalan todas las dependencias necesarias con la versión correcta
    implementation(platform("io.insert-koin:koin-bom:3.5.6"))
    implementation("io.insert-koin:koin-core") // Core
    implementation("io.insert-koin:koin-test") // Para test y usar checkModules
    // Para las anotaciones
    implementation(platform("io.insert-koin:koin-annotations-bom:1.3.1")) // BOM
    implementation("io.insert-koin:koin-annotations") // Annotations
    ksp("io.insert-koin:koin-ksp-compiler:1.3.1") // KSP Compiler, debes poner el mismo que el de las anotaciones
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "MainKt"
    }
    configurations["compileClasspath"].forEach { file: File ->
        from(zipTree(file.absoluteFile))
    }
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}

// Configuración del plugin de SqlDeLight
sqldelight {
    databases {
        // Nombre de la base de datos
        create("Database") {
            // Paquete donde se generan las clases
            packageName.set("org.example.database")
        }
    }
}