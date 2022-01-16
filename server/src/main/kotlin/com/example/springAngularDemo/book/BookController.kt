package com.example.springAngularDemo.book

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid


@RestController
@RequestMapping("/books")
@CrossOrigin(origins = ["http://localhost:4200"])
class BookController(
    private val bookRepository: BookRepository
) {
    @PostMapping
    fun saveBook(@Valid @RequestBody bookDetails: BookSaveRequest) =
        ResponseEntity.ok(bookRepository.save(bookDetails.toEntity()))

    @GetMapping
    fun getAll(): ResponseEntity<MutableList<BookEntity?>> =
        ResponseEntity.ok(bookRepository.findAll())

    @GetMapping("/{bookId}")
    fun get(@PathVariable("bookId") bookId: UUID) =
        ResponseEntity.ok(bookRepository.getById(bookId))
}
