package com.arteno.saci.service

import com.arteno.saci.dao.InventarioDao
import com.arteno.saci.document.Inventario
import org.springframework.dao.DuplicateKeyException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class InventarioService(private val dao: InventarioDao):CrudSimple<Inventario, String> {

    override fun obtenerTodos():List<Inventario> = this.dao.findAll()

    override fun obtenerPorId(id: String): Inventario? = this.dao.findByIdOrNull(id)

    override fun guardar(t: Inventario): Inventario {
        return if (!this.dao.existsById(t.id))
            this.dao.save(t)
        else
            throw DuplicateKeyException("El producto ${t.catProducto} con el identificador ${t.id} ya existe en el inventario")
    }

    override fun actualizar(t: Inventario): Inventario {
        return if (this.dao.existsById(t.id))
            this.dao.save(t)
        else
            throw Exception("El producto ${t.catProducto} con el identificador ${t.id} no se puede actualizar por que no existe en el inventario")
    }

    override fun eliminarPorId(id: String): Inventario {
        return this.obtenerPorId(id)?.apply {
            this@InventarioService.dao.deleteById(this.id)
        } ?: throw Exception("El producto con el identificador ${id} no se puede eliminar por que no existe en el inventario")
    }
}