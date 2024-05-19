package dev.jaimeleon.di

import dev.jaimeleon.database.service.SqlDeLightManager
import dev.jaimeleon.productos.services.database.DataBaseManager
import org.koin.dsl.module

/**
 * Modulo de carga para la base de datos
 * @author Jaime Leon
 * @since 1.0.0
 */
val databaseModule = module {
    single {
        SqlDeLightManager(
            databaseUrl = getProperty("database.url", "jdbc:sqlite:cineJaimeGerman.db"),
            databaseInitData = getProperty("database.init.data", "true").toBoolean(),
            databaseInMemory = getProperty("database.inmemory", "true").toBoolean()
        )
    }

    single<DataBaseManager> { DataBaseManager }
}