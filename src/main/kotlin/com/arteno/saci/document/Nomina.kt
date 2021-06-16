package com.arteno.saci.document

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import javax.validation.constraints.Min

@Document(collation = "Nomina")
data class Nomina (
    @Id
    var id:String,

    val empleado: Empleado,

    @get:Min(0)
    val pagoNomina:Double){

    override fun equals(other:Any?): Boolean {
        other ?: return false
        if (other === this) return true
        if (this.javaClass != other.javaClass) return false
        other as Nomina

        return this.id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}