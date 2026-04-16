package com.tiesen.api.utils

import com.fasterxml.jackson.annotation.JsonInclude
import java.time.Instant
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Response<T>(
    val timestamp: String = Instant.now().toString(),
    val status: Int,
    val message: String,
    val data: T? = null,
) {
  companion object {
    fun <T> ok(message: String, data: T? = null): ResponseEntity<Response<T>> {
      return ResponseEntity.ok(
          Response(status = HttpStatus.OK.value(), message = message, data = data)
      )
    }

    fun <T> created(message: String, data: T? = null): ResponseEntity<Response<T>> {
      return ResponseEntity.status(HttpStatus.CREATED)
          .body(Response(status = HttpStatus.CREATED.value(), message = message, data = data))
    }

    fun error(status: HttpStatus, error: String): ResponseEntity<Response<Nothing>> {
      return ResponseEntity.status(status).body(Response(status = status.value(), message = error))
    }
  }
}
