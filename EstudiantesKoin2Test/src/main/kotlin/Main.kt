package dev.jaimeleon

import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.context.GlobalContext.startKoin
import org.koin.fileProperties
import org.koin.ksp.generated.defaultModule
import org.koin.test.verify.verify

@OptIn(KoinExperimentalAPI::class)
fun main() {

    startKoin {
        printLogger()
        fileProperties("/config.properties")
        defaultModule.verify(extraTypes = listOf(Boolean::class, Int::class))
        modules(defaultModule)
    }

    val app = EstudiantesApp()
    app.run()
}