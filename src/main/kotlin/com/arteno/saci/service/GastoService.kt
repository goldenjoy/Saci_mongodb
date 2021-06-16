package com.arteno.saci.service

import com.arteno.saci.dao.GastoDao
import com.arteno.saci.document.Gasto
import org.springframework.dao.DuplicateKeyException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class GastoService(private val dao: GastoDao):CrudSimple<Gasto, String> {

    override fun obtenerTodos():List<Gasto> = this.dao.findAll()

    override fun obtenerPorId(id: String): Gasto? = this.dao.findByIdOrNull(id)

    override fun guardar(t: Gasto): Gasto {
        return if (!this.dao.existsById(t.id))
            this.dao.save(t)
        else
            throw DuplicateKeyException("El gasto con el identificador ${t.id} ya existe")
    }

    override fun actualizar(t: Gasto): Gasto {
        return if (this.dao.existsById(t.id))
            this.dao.save(t)
        else
            throw Exception("El gasto con el identificador ${t.id} no se puede actualizar por que no existe")
    }

    override fun eliminarPorId(id: String): Gasto {
        return this.obtenerPorId(id)?.apply {
            this@GastoService.dao.deleteById(this.id)
        } ?: throw Exception("El gasto con el identificador ${id} no se puede eliminar por que no existe")
    }
}