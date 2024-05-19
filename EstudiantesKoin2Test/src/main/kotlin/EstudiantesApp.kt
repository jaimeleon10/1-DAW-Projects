package dev.jaimeleon

import com.github.michaelbull.result.onFailure
import com.github.michaelbull.result.onSuccess
import dev.jaimeleon.estudiantes.models.Estudiante
import dev.jaimeleon.estudiantes.services.EstudiantesService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named
import org.lighthousegames.logging.logging

private val logger = logging()

class EstudiantesApp : KoinComponent {
    fun run() {
        val estudiantesService: EstudiantesService by inject(named("EstudiantesService"))

        logger.warn { "Imprimiendo todos los estudiantes..." }
        estudiantesService.getAll().value.forEach { println(it) }

        println()

        logger.warn { "Imprimiendo estudiante con id 8..." }
        estudiantesService.getById(8).onSuccess {
            println(it)
        }.onFailure {
            println("ERROR: ${it.message}")
        }

        println()

        logger.warn { "Guardando nuevo estudiante..." }
        val e1 = Estudiante(id = 11, nombre = "NuevoEstudiante", calificacion = 8)
        estudiantesService.save(e1)
            .onSuccess { estudiante ->
                println("Estudiante '${estudiante.nombre}' guardado con éxito")
                logger.warn { "Imprimiendo estudiante guardado..." }
                estudiantesService.getById(estudiante.id).onSuccess { println(it) }.onFailure { println("ERROR: ${it.message}") }
            }
            .onFailure { println("ERROR: ${it.message}") }

        println()

        logger.warn { "Actualizando estudiante con id 3..." }
        val eUpdate = Estudiante(id = 3, nombre = "EstudianteUpdate", calificacion = 1)
        estudiantesService.update(3, eUpdate).onSuccess { estudiante ->
            logger.warn { "Imprimiendo estudiante actualizado..." }
            estudiantesService.getById(estudiante.id).onSuccess { println(it) }.onFailure { println("ERROR: ${it.message}") }
        }.onFailure { println("ERROR: ${it.message}") }


        println()

        logger.warn { "Borrando estudiante con id 11..." }
        estudiantesService.delete(11).onSuccess { estudiante ->
            logger.warn { "Imprimiendo estudiante eliminado..." }
            estudiantesService.getById(estudiante.id).onSuccess { println(it) }.onFailure { println("ERROR: ${it.message}") }
        }.onFailure { println("ERROR: ${it.message}") }


        println()

        logger.warn { "Imprimiendo todos los estudiantes..." }
        estudiantesService.getAll().value.forEach { println(it) }
    }
}