package com.arteno.saci.service

import com.arteno.saci.dao.CatProductoDao
import com.arteno.saci.document.CatProducto
import org.springframework.dao.DuplicateKeyException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CatProductoService(private val daoCat: CatProductoDao):CrudSimple<CatProducto, String> {

    override fun obtenerTodos():List<CatProducto> = this.daoCat.findAll()

    override fun obtenerPorId(id: String): CatProducto? = this.daoCat.findByIdOrNull(id)

    override fun guardar(t: CatProducto): CatProducto {
        return if (!this.daoCat.existsByDesc(t.desc))
            this.daoCat.save(t)
        else
            throw DuplicateKeyException("El producto ${t.desc} con el identificador ${t.id} ya existe en el catálogo")
    }

    override fun actualizar(t: CatProducto): CatProducto {
        return if (this.daoCat.existsById(t.id))
            this.daoCat.save(t)
        else
            throw Exception("El producto ${t.desc} con el identificador ${t.id} no se puede actualizar por que no existe en el catálogo")
    }

    override fun eliminarPorId(id: String): CatProducto {
        return this.obtenerPorId(id)?.apply {
            this@CatProductoService.daoCat.deleteById(this.id)
        } ?: throw Exception("El producto con el identificador ${id} no se puede eliminar por que no existe en el catálogo")
    }
}