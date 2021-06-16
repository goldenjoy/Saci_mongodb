package com.arteno.saci.service

import com.arteno.saci.dao.NominaDao
import com.arteno.saci.document.Nomina
import org.springframework.dao.DuplicateKeyException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class NominaService(private val dao: NominaDao):CrudSimple<Nomina, String> {

    override fun obtenerTodos():List<Nomina> = this.dao.findAll()

    override fun obtenerPorId(id: String): Nomina? = this.dao.findByIdOrNull(id)

    override fun guardar(t: Nomina): Nomina {
        return if (!this.dao.existsById(t.id))
            this.dao.save(t)
        else
            throw DuplicateKeyException("El registro de nomina con el identificador ${t.id} ya existe")
    }

    override fun actualizar(t: Nomina): Nomina {
        return if (this.dao.existsById(t.id))
            this.dao.save(t)
        else
            throw Exception("El registro de nomina con el identificador ${t.id} no se puede actualizar por que no existe")
    }

    override fun eliminarPorId(id: String): Nomina {
        return this.obtenerPorId(id)?.apply {
            this@NominaService.dao.deleteById(this.id)
        } ?: throw Exception("El registro de nomina con el identificador ${id} no se puede eliminar por que no existe")
    }
}