package estudiantes.repositories

import dev.jaimeleon.database.service.SqlDeLightManager
import dev.jaimeleon.estudiantes.models.Estudiante
import dev.jaimeleon.estudiantes.repositories.EstudiantesRepository
import dev.jaimeleon.estudiantes.repositories.EstudiantesRepositoryImpl
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestInstance
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.fileProperties
import org.koin.ksp.generated.defaultModule
import org.koin.test.inject
import org.koin.test.junit5.AutoCloseKoinTest
import kotlin.test.Test

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EstudiantesRepositoryImplTest : AutoCloseKoinTest() {

    private val dbManager: SqlDeLightManager by inject()
    private val estudiantesRepository: EstudiantesRepository by inject(named("EstudiantesRepository"))

    @BeforeAll
    fun setUpAll() {
        // Inicializamos los modulos que necesitamos
        println("Inicializando todos los tests...")
        startKoin {
            fileProperties("/config.properties")
            modules(defaultModule)
        }
    }

    @BeforeEach
    fun setUp() {
        // Inicializamos la base de datos
        dbManager.initialize()
    }


    @Test
    fun findAll() {
        val estudiantes = estudiantesRepository.findAll()
        assertEquals(10, estudiantes.size)
    }

    @Test
    fun findById() {
        val estudiante = estudiantesRepository.findById(1)
        assertAll(
            { assertEquals(1, estudiante?.id) },
            { assertEquals("Estudiante1", estudiante?.nombre) },
            { assertEquals(1, estudiante?.calificacion) }
        )
    }

    @Test
    fun findByIdNotFound() {
        val estudiante = estudiantesRepository.findById(100)
        assertEquals(null, estudiante)
    }

    @Test
    fun save() {
        val estudiante = estudiantesRepository.save(
            Estudiante(
                nombre = "TEST1",
                calificacion = 2,
            )
        )
        assertAll(
            { assertEquals(11, estudiante.id) },
            { assertEquals("TEST1", estudiante.nombre) },
            { assertEquals(2, estudiante.calificacion) }
        )
    }

    @Test
    fun update() {
        val estudiante = estudiantesRepository.update(
            1,
            Estudiante(
                id = 1,
                nombre = "TEST-UPDATE",
                calificacion = 10
            )
        )
        println(estudiante)
        assertAll(
            { assertEquals(1, estudiante?.id) },
            { assertEquals("TEST-UPDATE", estudiante?.nombre) },
            { assertEquals(10, estudiante?.calificacion) }
        )
    }

    @Test
    fun updateNotFound() {
        val estudiante = estudiantesRepository.update(
            -1,
            Estudiante(
                id = -1,
                nombre = "TEST-UPDATE",
                calificacion = 10
            )
        )
        assertEquals(null, estudiante)
    }

    @Test
    fun delete() {
        val estudiante = estudiantesRepository.delete(1)
        assertAll(
            { assertEquals(1, estudiante?.id) },
            { assertEquals("Estudiante1", estudiante?.nombre) },
            { assertEquals(1, estudiante?.calificacion) }
        )
    }

    @Test
    fun deleteNotFound() {
        val estudiante = estudiantesRepository.delete(-1)
        assertEquals(null, estudiante)
    }
}
