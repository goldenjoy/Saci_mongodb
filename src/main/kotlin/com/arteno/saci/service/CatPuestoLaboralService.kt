package com.arteno.saci.service

import com.arteno.saci.dao.CatPuestoLaboralDao
import com.arteno.saci.document.CatPuestoLaboral
import org.springframework.dao.DuplicateKeyException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CatPuestoLaboralService(private val daoCat: CatPuestoLaboralDao):CrudSimple<CatPuestoLaboral, String> {

    override fun obtenerTodos():List<CatPuestoLaboral> = this.daoCat.findAll()

    override fun obtenerPorId(id: String): CatPuestoLaboral? = this.daoCat.findByIdOrNull(id)

    override fun guardar(t: CatPuestoLaboral): CatPuestoLaboral {
        return if (!this.daoCat.existsByDesc(t.desc))
            this.daoCat.save(t)
        else
            throw DuplicateKeyException("El puesto laboral ${t.desc} ya esta registrado en el sistema y no puede duplicarse")
    }

    override fun actualizar(t: CatPuestoLaboral): CatPuestoLaboral {
        return if (this.daoCat.existsById(t.id))
            this.guardar(t)
        else
            throw Exception("El puesto laboral ${t.desc} con el identificador ${t.id} no se puede actualizar por que no existe en el catálogo")
    }

    override fun eliminarPorId(id: String): CatPuestoLaboral {
        return this.obtenerPorId(id)?.apply {
            this@CatPuestoLaboralService.daoCat.deleteById(this.id)
        } ?: throw Exception("El puesto laboral con el identificador ${id} no se puede eliminar por que no existe en el catálogo")
    }
}