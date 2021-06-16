package com.arteno.saci.service

import com.arteno.saci.dao.MermaDao
import com.arteno.saci.document.Merma
import org.springframework.dao.DuplicateKeyException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class MermaService(private val dao: MermaDao):CrudSimple<Merma, String> {

    override fun obtenerTodos():List<Merma> = this.dao.findAll()

    override fun obtenerPorId(id: String): Merma? = this.dao.findByIdOrNull(id)

    override fun guardar(t: Merma): Merma {
        return if (!this.dao.existsById(t.id))
            this.dao.save(t)
        else
            throw DuplicateKeyException("La merma con el identificador ${t.id} ya existe")
    }

    override fun actualizar(t: Merma): Merma {
        return if (this.dao.existsById(t.id))
            this.dao.save(t)
        else
            throw Exception("La merma con el identificador ${t.id} no se puede actualizar por que no existe")
    }

    override fun eliminarPorId(id: String): Merma {
        return this.obtenerPorId(id)?.apply {
            this@MermaService.dao.deleteById(this.id)
        } ?: throw Exception("La merma con el identificador ${id} no se puede eliminar por que no existe")
    }
}