package dev.jaimeleon.database.service

import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import database.DatabaseQueries
import dev.jaimeleon.database.data.initDemoEstudiantes
import org.example.database.Database
import org.koin.core.annotation.Property
import org.koin.core.annotation.Singleton
import org.lighthousegames.logging.logging

private val logger = logging()

@Singleton
class SqlDeLightManager(
    @Property("database.url")
    private val _databaseUrl: String = "jdbc:sqlite:productos.db",
    @Property("database.inmemory")
    private val _databaseInMemory: String = "true",
    @Property("database.init.data")
    private val _databaseInitData: String = "true"
) {
    private val databaseUrl: String = _databaseUrl
    private val databaseInitData: Boolean = _databaseInitData.toBoolean()
    private val databaseInMemory: Boolean = _databaseInMemory.toBoolean()
    val databaseQueries: DatabaseQueries by lazy { initQueries() }

    init {
        logger.debug { "Inicializando el gestor de Bases de Datos con SQLDelight" }
        initialize()
    }

    private fun initQueries(): DatabaseQueries {

        return if (databaseInMemory) {
            logger.debug { "SqlDeLightClient - InMemory" }
            JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
        } else {
            logger.debug { "SqlDeLightClient - File: ${databaseUrl}" }
            JdbcSqliteDriver(databaseUrl)
        }.let { driver ->
            // Creamos la base de datos
            logger.debug { "Creando Tablas (si es necesario)" }
            Database.Schema.create(driver)
            Database(driver)
        }.databaseQueries

    }

    fun initialize() {
        if (databaseInitData) {
            removeAllData()
            initDataExamples()
        }
    }

    private fun initDataExamples() {
        logger.debug { "Iniciando datos de ejemplo" }
        databaseQueries.transaction {
            demoEstudiantes()
        }
    }

    private fun demoEstudiantes() {
        logger.debug { "Datos de ejemplo de Productos" }
        initDemoEstudiantes().forEach {
            databaseQueries.insertEstudiante(
                nombre = it.nombre,
                calificacion = it.calificacion.toLong(),
            )
        }
    }

    // limpiamos las tablas
    private fun removeAllData() {
        logger.debug { "SqlDeLightClient.removeAllData()" }
        databaseQueries.transaction {
            databaseQueries.removeAllEstudiantes()
        }
    }
}