package com.arteno.saci.controller

import com.arteno.saci.document.*
import com.arteno.saci.service.*
import org.bson.types.ObjectId
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/service/periodoNomina")
class CatPeriodoNominaController(serviceCat: CatPeriodoNominaService): CrudComun<CatPeriodoNomina, String>(serviceCat)

@RestController
@RequestMapping("/service/producto")
class CatProductoController(serviceCat: CatProductoService): CrudComun<CatProducto, String>(serviceCat)

@RestController
@RequestMapping("/service/puestoLaboral")
class CatPuestoLaboralController(serviceCat: CatPuestoLaboralService): CrudComun<CatPuestoLaboral, String>(serviceCat)

@RestController
@RequestMapping("/service/unidadMedida")
class CatUnidadMedidaController(serviceCat: CatUnidadMedidaService): CrudComun<CatUnidadMedida, ObjectId>(serviceCat)

@RestController
@RequestMapping("/service/empleado")
class EmpleadoController(service: EmpleadoService): CrudComun<Empleado, String>(service)

@RestController
@RequestMapping("/service/gasto")
class GastoController(service: GastoService): CrudComun<Gasto, String>(service)

@RestController
@RequestMapping("/service/inventario")
class InventarioController(service: InventarioService): CrudComun<Inventario, String>(service)

@RestController
@RequestMapping("/service/merma")
class MermaController(service: MermaService): CrudComun<Merma, String>(service)

@RestController
@RequestMapping("/service/produccion")
class ProduccionController(service: ProduccionService): CrudComun<Produccion, String>(service)

@RestController
@RequestMapping("/service/nomina")
class NominaController(service: NominaService): CrudComun<Nomina, String>(service)

@RestController
@RequestMapping("/service/venta")
class VentaController(service: VentaService): CrudComun<Venta, String>(service)