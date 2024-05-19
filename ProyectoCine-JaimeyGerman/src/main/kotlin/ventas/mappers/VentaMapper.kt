package dev.jaimeleon.ventas.mappers

import database.LineaVentaEntity
import database.VentaEntity
import dev.jaimeleon.clientes.mappers.toClienteDto
import dev.jaimeleon.clientes.models.Cliente
import dev.jaimeleon.productos.mappers.toProductoDto
import dev.jaimeleon.productos.models.butacas.Butaca
import dev.jaimeleon.productos.models.complementos.Complemento
import dev.jaimeleon.productos.models.productos.Producto
import dev.jaimeleon.ventas.dto.LineaVentaDto
import dev.jaimeleon.ventas.dto.VentaDto
import dev.jaimeleon.ventas.models.LineaVenta
import dev.jaimeleon.ventas.models.Venta
import java.time.LocalDate
import java.util.*

/**
 * Función para el traspaso de LineaVentaEntity a LineaVenta
 * @param producto producto para la creacion
 * @author German Fernandez
 * @since 1.0.0
 * @return Linea de venta
 */
fun LineaVentaEntity.toLineaVenta(producto: Producto): LineaVenta {
    return LineaVenta(
        id = UUID.fromString(this.id),
        producto = producto,
        tipoProducto = this.producto_tipo,
        cantidad = this.cantidad.toInt(),
        precio = this.precio,
        createdAt = LocalDate.parse(this.created_at),
        updatedAt = LocalDate.parse(this.updated_at),
    )
}

/**
 * Función para el traspaso de VentaEntity a Venta
 * @param cliente cliente para la creacion
 * @param lineas lineas de venta para la creacion
 * @param fechaCompra fecha de compra de la venta
 * @author German Fernandez
 * @since 1.0.0
 * @return Venta
 */
fun VentaEntity.toVenta(cliente: Cliente, lineas: List<LineaVenta>, fechaCompra: LocalDate): Venta {
    return Venta(
        id = UUID.fromString(this.id),
        cliente = cliente,
        lineas = lineas,
        fechaCompra = fechaCompra,
        createdAt = LocalDate.parse(this.created_at),
        updatedAt = LocalDate.parse(this.updated_at),
    )
}

/**
 * Función para el traspaso de LineaVenta a LineaVentaDto
 * @author German Fernandez
 * @since 1.0.0
 * @return LineaVentaDto
 */
fun LineaVenta.toLineaVentaDto(): LineaVentaDto {
    return when (this.producto) {
        is Butaca -> {
            LineaVentaDto(
                id = this.id.toString(),
                producto = this.producto.toProductoDto(),
                tipoProducto = "Butaca",
                cantidad = this.cantidad,
                precio = this.precio,
                createdAt = this.createdAt.toString(),
                updatedAt = this.updatedAt.toString(),
            )
        }

        is Complemento -> {
            LineaVentaDto(
                id = this.id.toString(),
                producto = this.producto.toProductoDto(),
                tipoProducto = "Complemento",
                cantidad = this.cantidad,
                precio = this.precio,
                createdAt = this.createdAt.toString(),
                updatedAt = this.updatedAt.toString(),
            )
        }

        else -> throw IllegalArgumentException("Tipo de producto no soportado")
    }
}

/**
 * Función para el traspaso de Venta a VentaDto
 * @author German Fernandez
 * @since 1.0.0
 * @return VentaDto
 */
fun Venta.toVentaDto(): VentaDto {
    return VentaDto(
        id = this.id.toString(),
        cliente = this.cliente.toClienteDto(),
        lineas = this.lineas.map { it.toLineaVentaDto() },
        total = this.lineas.sumOf { it.precio },
        fechaCompra = this.fechaCompra.toString(),
        createdAt = this.createdAt.toString(),
        updatedAt = this.updatedAt.toString(),
        isDeleted = this.isDeleted
    )
}