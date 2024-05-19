package dev.jaimeleon.ventas.repositories


import com.github.michaelbull.result.Result
import database.VentaEntity
import dev.jaimeleon.clientes.models.Cliente
import dev.jaimeleon.ventas.errors.VentaError
import dev.jaimeleon.ventas.models.LineaVenta
import dev.jaimeleon.ventas.models.Venta
import java.time.LocalDate
import java.util.*

/**
 * Interfaz para la gestion del repositorio de ventas
 * @author German Fernandez
 * @since 1.0.0
 */
interface VentaRepository {
    fun findAll(cliente: Cliente, lineas: List<LineaVenta>, fechaCompra:LocalDate): List<Venta>
    fun findById(id: UUID): Venta?
    fun save(venta: Venta): Venta
    fun update(id: UUID, venta: Venta): Venta?
    fun delete (id: UUID): Venta?
    fun totalVentasByDate(fechaCompra: LocalDate): List<VentaEntity>
    fun validateCliente(cliente: Cliente): Result<Cliente, VentaError>
    fun validateLineas(lineas: List<LineaVenta>): Result<List<LineaVenta>, VentaError>
    fun actualizarStock(lineas: List<LineaVenta>): Result<List<LineaVenta>, VentaError>
}