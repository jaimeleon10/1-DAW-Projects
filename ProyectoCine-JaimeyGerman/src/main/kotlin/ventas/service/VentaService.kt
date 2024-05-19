package dev.jaimeleon.ventas.service

import com.github.michaelbull.result.Result
import database.VentaEntity
import dev.jaimeleon.clientes.models.Cliente
import dev.jaimeleon.ventas.errors.VentaError
import dev.jaimeleon.ventas.models.LineaVenta
import dev.jaimeleon.ventas.models.Venta
import java.io.File
import java.time.LocalDate
import java.util.*

/**
 * Interfaz para el manejo del servicio de ventas
 * @author German Fernandez
 * @since 1.0.0
 */
interface VentaService {
    fun getById(id: UUID): Result<Venta, VentaError>
    fun createVenta(venta: Venta): Result<Venta, VentaError>
    fun totalVentasByDate(fechaCompra: LocalDate): List<VentaEntity>
    fun exportToJson(venta: Venta, jsonFile: File): Result<Unit, VentaError>
    fun exportCompraToHtml(venta: Venta, htmlFile: File): Result<Unit, VentaError>
    fun exportDevolucionToHtml(venta: Venta, htmlFile: File): Result<Unit, VentaError>
}