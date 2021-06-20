package com.arteno.saci.document

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import javax.validation.constraints.Size

@Document(collection = "catUnidadMedida")
data class CatUnidadMedida (
    @Id
    var id: ObjectId = ObjectId(),

    @get:Size(min = 3, max = 25)
    val desc:String){

    override fun equals(other:Any?): Boolean {
        other ?: return false
        if (other === this) return true
        if (this.javaClass != other.javaClass) return false
        other as CatUnidadMedida

        return this.id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}