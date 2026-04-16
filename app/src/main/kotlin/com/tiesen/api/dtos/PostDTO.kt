package com.tiesen.api.dtos

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class PostDTO(
    @field:NotBlank(message = "Username is required")
    @field:Size(min = 4, max = 20, message = "Username must be between 4 and 20 characters")
    val title: String,
    @field:NotBlank(message = "Content is required")
    @field:Size(min = 10, max = 1000, message = "Content must be between 10 and 1000 characters")
    val content: String,
)
