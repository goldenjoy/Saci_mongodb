package com.arteno.saci.dao

import com.arteno.saci.document.*
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface CatPeriodoNominaDao: MongoRepository<CatPeriodoNomina, String>{
    fun existsByDesc(desc: String): Boolean
}

@Repository
interface CatProductoDao: MongoRepository<CatProducto, String>{
    fun existsByDesc(@Param("desc") desc: String): Boolean
}

@Repository
interface CatPuestoLaboralDao: MongoRepository<CatPuestoLaboral, String>{
    fun existsByDesc(@Param("desc") desc: String): Boolean
}

@Repository
interface CatUnidadMedidaDao: MongoRepository<CatUnidadMedida, ObjectId>{
    fun existsByDesc(@Param("desc") desc: String): Boolean
}

@Repository
interface EmpleadoDao: MongoRepository<Empleado, String>

@Repository
interface GastoDao: MongoRepository<Gasto, String>

@Repository
interface InventarioDao: MongoRepository<Inventario, String>

@Repository
interface MermaDao: MongoRepository<Merma, String>

@Repository
interface ProduccionDao: MongoRepository<Produccion, String>

@Repository
interface NominaDao: MongoRepository<Nomina, String>

@Repository
interface VentaDao: MongoRepository<Venta, String>

