package dev.jaimeleon.config

import org.koin.core.annotation.Property
import org.koin.core.annotation.Singleton
import org.lighthousegames.logging.logging
import java.nio.file.Files
import kotlin.io.path.Path

private val logger = logging()

@Singleton
class Config (
    @Property("database.url")
    _databaseUrl: String = "jdbc:sqlite:estudiantes.db",
    @Property("database.init.tables")
    _databaseInitTables: Boolean = true,
    @Property("database.init.data")
    _databaseInitData: Boolean = true,
    @Property("database.inmemory")
    _databaseInMemory: Boolean = true,
    @Property("storage.data")
    _storageData: String = "data",
    @Property("cache.size")
    _cacheSize: Int = 6
) {

    val databaseUrl: String = _databaseUrl
    val databaseInitTables: Boolean = _databaseInitTables
    val databaseInitData: Boolean = _databaseInitData
    val databaseInMemory: Boolean = _databaseInMemory
    val storageData: String = _storageData
    val cacheSize: Int = _cacheSize.toInt()

    init {
        try {
            logger.debug { "Cargando configuración" }
            // crear el directorio si no existe
            Files.createDirectories(Path(storageData))

        } catch (e: Exception) {
            logger.error { "Error cargando configuración: ${e.message}" }
        }
    }
}