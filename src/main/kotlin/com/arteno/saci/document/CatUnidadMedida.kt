package com.arteno.saci.document

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import javax.validation.constraints.Size

@Document(collation = "catUnidadMedida")
data class CatUnidadMedida (
    @Id
    var _id:String,

    @get:Size(min = 3, max = 25)
    var desc:String){

    override fun equals(other:Any?): Boolean {
        other ?: return false
        if (other === this) return true
        if (this.javaClass != other.javaClass) return false
        other as CatUnidadMedida

        return this._id == other._id
    }

    override fun hashCode(): Int {
        return _id.hashCode()
    }
}