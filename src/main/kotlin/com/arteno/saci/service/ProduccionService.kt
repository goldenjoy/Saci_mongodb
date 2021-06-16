package com.arteno.saci.service

import com.arteno.saci.dao.ProduccionDao
import com.arteno.saci.document.Produccion
import org.springframework.dao.DuplicateKeyException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ProduccionService(private val dao: ProduccionDao):CrudSimple<Produccion, String> {

    override fun obtenerTodos():List<Produccion> = this.dao.findAll()

    override fun obtenerPorId(id: String): Produccion? = this.dao.findByIdOrNull(id)

    override fun guardar(t: Produccion): Produccion {
        return if (!this.dao.existsById(t.id))
            this.dao.save(t)
        else
            throw DuplicateKeyException("El registro de producción con el identificador ${t.id} ya existe")
    }

    override fun actualizar(t: Produccion): Produccion {
        return if (this.dao.existsById(t.id))
            this.dao.save(t)
        else
            throw Exception("El registro de producción con el identificador ${t.id} no se puede actualizar por que no existe")
    }

    override fun eliminarPorId(id: String): Produccion {
        return this.obtenerPorId(id)?.apply {
            this@ProduccionService.dao.deleteById(this.id)
        } ?: throw Exception("El registro de producción con el identificador ${id} no se puede eliminar por que no existe")
    }
}