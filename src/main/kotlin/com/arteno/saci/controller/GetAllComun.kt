package com.arteno.saci.controller

import com.arteno.saci.service.CrudSimple
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

abstract class GetAllComun<T, ID>(private val crudBasico: CrudSimple<T, ID>, private val nameView: String) {

    @GetMapping()
    fun mostrarTodos(model: Model):String{
        model.addAttribute("listado", crudBasico.obtenerTodos())
        return nameView
    }


}