package dev.jaimeleon.estudiantes.cache

import dev.jaimeleon.cache.CacheImpl
import dev.jaimeleon.estudiantes.models.Estudiante
import org.koin.core.annotation.Property
import org.koin.core.annotation.Singleton

@Singleton
class EstudiantesCache(@Property("cache.size") _size: String = "6"): CacheImpl<Long, Estudiante>(_size.toInt()) {
    val cacheSize: Int = _size.toInt()
}