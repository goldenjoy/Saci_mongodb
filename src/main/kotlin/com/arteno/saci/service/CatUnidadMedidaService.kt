package com.arteno.saci.service

import com.arteno.saci.dao.CatUnidadMedidaDao
import com.arteno.saci.document.CatUnidadMedida
import org.springframework.dao.DuplicateKeyException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CatUnidadMedidaService(private val daoCat: CatUnidadMedidaDao):CrudSimple<CatUnidadMedida, String> {

    override fun obtenerTodos():List<CatUnidadMedida> = this.daoCat.findAll()

    override fun obtenerPorId(id: String): CatUnidadMedida? = this.daoCat.findByIdOrNull(id)

    override fun guardar(t: CatUnidadMedida): CatUnidadMedida {
        return if (!this.daoCat.existsByDesc(t.desc))
            this.daoCat.save(t)
        else
            throw DuplicateKeyException("La unidad de medida ${t.desc} ya esta registrada en el sistema y no puede ser duplicada")
    }

    override fun actualizar(t: CatUnidadMedida): CatUnidadMedida {
        return if (this.daoCat.existsById(t._id))
            this.guardar(t)
        else
            throw Exception("La unidad de medida ${t.desc} no puede ser actualizada por que no existe en el sistema")
    }

    override fun eliminarPorId(id: String): CatUnidadMedida {
        return this.obtenerPorId(id)?.apply {
            this@CatUnidadMedidaService.daoCat.deleteById(this._id)
        } ?: throw Exception("La unidad de medida con el identificador ${id} no se puede eliminar por que no se encuentra en el sistema")
    }
}