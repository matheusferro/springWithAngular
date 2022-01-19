package com.example.springAngularDemo.book

import com.example.springAngularDemo.circuitBreaker.GenericCircuitBreaker
import com.example.springAngularDemo.config.BusinessException
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker
import org.springframework.http.ResponseEntity
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/books")
@CrossOrigin(origins = ["http://localhost:4200"])
class BookController(
    private val bookRepository: BookRepository,
    private val gCircuitBreaker: GenericCircuitBreaker<ResponseEntity<BookEntity>>
) {
    @PostMapping
    fun saveBook(@Valid @RequestBody bookDetails: BookSaveRequest) =
        ResponseEntity.ok(bookRepository.save(bookDetails.toEntity()))

    @GetMapping
    fun getAll(): ResponseEntity<MutableList<BookEntity?>> = ResponseEntity.ok(bookRepository.findAll())

    //    @Retry(name = BACKEND, fallbackMethod = "fallback")
//    @CircuitBreaker(name = BACKEND)
//    @RateLimiter(name = BACKEND)
//    @TimeLimiter(name = BACKEND, fallbackMethod = "fallback")
//    @Bulkhead(name = BACKEND)
    @GetMapping("/{bookId}")
    fun get(@PathVariable("bookId") bookId: UUID): ResponseEntity<BookEntity> {
        return gCircuitBreaker.exec(
            { getBookById(bookId) },
            { fallback(it) }
        )
    }

    fun getBookById(bookId: UUID): ResponseEntity<BookEntity> {
        println("[GET]")
//            throw BusinessException("deu ruim")
//        Thread.sleep(10500)
        return ResponseEntity.ok(bookRepository.getById(bookId))

    }

    fun fallback(e: Throwable): ResponseEntity<BookEntity> {
        println("[1]fallback $e")
        return ResponseEntity.ok(BookEntity(id = UUID.randomUUID(), title = "fallback throwable", author = "fallback throwable"))
    }

    fun fallback(bookId: UUID, ex: JpaObjectRetrievalFailureException): ResponseEntity<BookEntity> {
        println("[2]fallback $ex")
        return ResponseEntity.ok(BookEntity(id = bookId, title = "fallback1 throwable and id", author = "fallback throwable and id"))
    }

    fun fallback(bookId: UUID, ex: BusinessException): ResponseEntity<BookEntity> {
        println("[3]fallback $ex")
        return ResponseEntity.ok(BookEntity(id = bookId, title = "fallback businessException", author = "fallback businessException"))
    }
}
