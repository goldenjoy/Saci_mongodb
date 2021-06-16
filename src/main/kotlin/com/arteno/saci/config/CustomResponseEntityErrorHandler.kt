package com.arteno.saci.config

import com.arteno.saci.dto.ApiResponse
import org.springframework.dao.DuplicateKeyException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class CustomResponseEntityErrorHandler:ResponseEntityExceptionHandler() {

    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        val result: Map<String, List<String?>> = ex.bindingResult.fieldErrors.groupBy({it.field},{it.defaultMessage})
        return ResponseEntity.status(status).headers(headers).body(result)
    }

    @ExceptionHandler(DuplicateKeyException::class, Exception::class)
    fun handleJpa(ex:Exception): ResponseEntity<ApiResponse> {
    return ResponseEntity.status(HttpStatus.CONFLICT).body(ApiResponse(
            titulo = ex::class.simpleName.toString(),
            mensage = ex.localizedMessage
        ))
    }
}