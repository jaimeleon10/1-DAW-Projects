package dev.jaimeleon.di

import dev.jaimeleon.productos.repositories.butacas.ButacaRepository
import dev.jaimeleon.productos.repositories.butacas.ButacaRepositoryImpl
import dev.jaimeleon.productos.repositories.complementos.ComplementoRepository
import dev.jaimeleon.productos.repositories.complementos.ComplementoRepositoryImpl
import dev.jaimeleon.productos.services.cache.butacas.ButacaCache
import dev.jaimeleon.productos.services.cache.complementos.ComplementoCache
import dev.jaimeleon.productos.services.productos.ProductoService
import dev.jaimeleon.productos.services.productos.ProductoServiceImpl
import dev.jaimeleon.productos.services.storage.StorageButacas
import dev.jaimeleon.productos.services.storage.StorageComplementos
import org.koin.dsl.module
import kotlin.math.sin

/**
 * Modulo de carga para los productos
 * @author Jaime Leon
 * @since 1.0.0
 */
val productoModule = module {
    single<ButacaRepository> { ButacaRepositoryImpl() }
    single<ComplementoRepository> { ComplementoRepositoryImpl() }
    single<ButacaCache> { ButacaCache() }
    single<ComplementoCache> { ComplementoCache() }
    single<StorageComplementos> { StorageComplementos() }
    single<StorageButacas> { StorageButacas() }

    single<ProductoService> {
        ProductoServiceImpl(
            butacaRepository = get(),
            complementoRepository = get(),
            butacaCache = get(),
            complementoCache = get(),
            storageComplementos = get(),
            storageButaca = get()
        )
    }
}
