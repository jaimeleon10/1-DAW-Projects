package dev.jaimeleon.di

import dev.jaimeleon.ventas.repositories.VentaRepository
import dev.jaimeleon.ventas.repositories.VentaRepositoryImpl
import dev.jaimeleon.ventas.service.VentaService
import dev.jaimeleon.ventas.service.VentaServiceImpl
import dev.jaimeleon.ventas.storage.VentaStorageHtmlCompraImpl
import dev.jaimeleon.ventas.storage.VentaStorageHtmlDevolucionImpl
import dev.jaimeleon.ventas.storage.VentaStorageJsonImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * Modulo de carga para las ventas
 * @author Jaime Leon
 * @since 1.0.0
 */
val ventaModule = module {
    // Ventas
    single<VentaRepository> {
        VentaRepositoryImpl(
            dbManager = get(),
            butacaRepository = get(),
            complementoRepository = get(),
            clienteRepository = get()
        )
    }
    single<VentaStorageJsonImpl> { VentaStorageJsonImpl() }
    single<VentaStorageHtmlCompraImpl> { VentaStorageHtmlCompraImpl() }
    single<VentaStorageHtmlDevolucionImpl> { VentaStorageHtmlDevolucionImpl() }
    single<VentaService> {
        VentaServiceImpl(
            ventaRepository = get(),
            ventaStorageJson = get(),
            ventaStorageHtmlCompra = get(),
            ventaStorageHtmlDevolucion = get()
        )
    }
}