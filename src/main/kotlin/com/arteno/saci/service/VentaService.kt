package com.arteno.saci.service

import com.arteno.saci.dao.VentaDao
import com.arteno.saci.document.Venta
import org.springframework.dao.DuplicateKeyException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class VentaService(private val dao: VentaDao):CrudSimple<Venta, String> {

    override fun obtenerTodos():List<Venta> = this.dao.findAll()

    override fun obtenerPorId(id: String): Venta? = this.dao.findByIdOrNull(id)

    override fun guardar(t: Venta): Venta {
        return if (!this.dao.existsById(t.id))
            this.dao.save(t)
        else
            throw DuplicateKeyException("La venta con el identificador ${t.id} ya existe")
    }

    override fun actualizar(t: Venta): Venta {
        return if (this.dao.existsById(t.id))
            this.dao.save(t)
        else
            throw Exception("La venta con el identificador ${t.id} no se puede actualizar por que no existe")
    }

    override fun eliminarPorId(id: String): Venta {
        return this.obtenerPorId(id)?.apply {
            this@VentaService.dao.deleteById(this.id)
        } ?: throw Exception("La venta con el identificador ${id} no se puede eliminar por que no existe")
    }
}