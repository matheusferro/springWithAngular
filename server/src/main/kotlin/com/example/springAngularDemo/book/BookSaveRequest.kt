package com.example.springAngularDemo.book

import java.util.*
import javax.validation.constraints.NotBlank

data class  BookSaveRequest(
    @field:NotBlank(message = "Field title is required.")
    val title: String,
    @field:NotBlank(message = "Field author is required.")
    val author: String
) {
    fun toEntity() = BookEntity(
        UUID.randomUUID(),
        title,
        author
    )
}