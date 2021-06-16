package com.arteno.saci.document

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import javax.validation.constraints.Min
import javax.validation.constraints.Size

@Document(collation = "CatProducto")
data class CatProducto (
    @Id
    var id:String,

    @get:Size(min = 3, max = 25)
    val desc:String,

    val idCatUnidadMedida:String,

    @get:Min(0)
    val precioUnitario:Double){

    override fun equals(other:Any?): Boolean {
        other ?: return false
        if (other === this) return true
        if (this.javaClass != other.javaClass) return false
        other as CatProducto

        return this.id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}