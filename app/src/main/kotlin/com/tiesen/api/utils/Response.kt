package com.tiesen.api.utils

import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Response<T>(val status: Int, val message: String, val data: T? = null) {
  companion object {
    fun <T> ok(message: String, data: T? = null): ResponseEntity<Response<T>> {
      return ResponseEntity.ok(Response(HttpStatus.OK.value(), message, data))
    }

    fun <T> created(message: String, data: T? = null): ResponseEntity<Response<T>> {
      return ResponseEntity.status(HttpStatus.CREATED)
          .body(Response(HttpStatus.CREATED.value(), message, data))
    }

    fun error(status: HttpStatus, message: String): ResponseEntity<Response<Nothing>> {
      return ResponseEntity.status(status).body(Response(status.value(), message))
    }
  }
}
