package com.arteno.saci.document

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import javax.validation.constraints.Min
import javax.validation.constraints.Size

@Document(collation = "Empleado")
data class Empleado (
    @Id
    var id:String,

    @get:Size(min = 3, max = 50)
    val nombre:String,

    val catPuestoLaboral: CatPuestoLaboral,

    @get:Min(0)
    val salarioPeriodo:Double,

    val idCatPeriodoNomina: String){

    override fun equals(other:Any?): Boolean {
        other ?: return false
        if (other === this) return true
        if (this.javaClass != other.javaClass) return false
        other as Empleado

        return this.id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}