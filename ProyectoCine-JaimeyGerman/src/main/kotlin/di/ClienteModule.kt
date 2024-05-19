package dev.jaimeleon.di

import dev.jaimeleon.clientes.repositories.ClienteRepository
import dev.jaimeleon.clientes.repositories.ClienteRepositoryImpl
import org.koin.dsl.module

/**
 * Modulo de carga para clientes
 * @author Jaime Leon
 * @since 1.0.0
 */
val clienteModule = module {
    single<ClienteRepository> { ClienteRepositoryImpl(dbManager = get()) }
}