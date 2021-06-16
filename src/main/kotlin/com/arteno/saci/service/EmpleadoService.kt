package com.arteno.saci.service

import com.arteno.saci.dao.EmpleadoDao
import com.arteno.saci.document.Empleado
import org.springframework.dao.DuplicateKeyException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class EmpleadoService(private val dao: EmpleadoDao):CrudSimple<Empleado, String> {

    override fun obtenerTodos():List<Empleado> = this.dao.findAll()

    override fun obtenerPorId(id: String): Empleado? = this.dao.findByIdOrNull(id)

    override fun guardar(t: Empleado): Empleado {
        return if (!this.dao.existsById(t.id))
            this.dao.save(t)
        else
            throw DuplicateKeyException("El empleado ${t.nombre} con el identificador ${t.id} ya existe")
    }

    override fun actualizar(t: Empleado): Empleado {
        return if (this.dao.existsById(t.id))
            this.dao.save(t)
        else
            throw Exception("El empleado ${t.nombre} con el identificador ${t.id} no se puede actualizar por que no existe")
    }

    override fun eliminarPorId(id: String): Empleado {
        return this.obtenerPorId(id)?.apply {
            this@EmpleadoService.dao.deleteById(this.id)
        } ?: throw Exception("El empleado  con el identificador ${id}  no se puede eliminar por que no existe")
    }
}