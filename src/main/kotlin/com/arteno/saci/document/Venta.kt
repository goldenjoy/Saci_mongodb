package com.arteno.saci.document

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import javax.validation.constraints.Min

@Document(collation = "Venta")
data class Venta (
    @Id
    var id:String,

    val catProducto: CatProducto,

    @get:Min(0)
    val cantidad:Double,

    @get:Min(0)
    val precioUnitario:Double,

    @get:Min(0)
    val totalVenta:Double){

    override fun equals(other:Any?): Boolean {
        other ?: return false
        if (other === this) return true
        if (this.javaClass != other.javaClass) return false
        other as Venta

        return this.id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}