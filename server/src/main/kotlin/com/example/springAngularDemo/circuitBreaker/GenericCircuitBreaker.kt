package com.example.springAngularDemo.circuitBreaker

import com.example.springAngularDemo.book.BookController
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker
import org.springframework.stereotype.Component

@Component
class GenericCircuitBreaker<T>(
    private val factory: Resilience4JCircuitBreakerFactory
) {
    companion object {
        private const val BACKEND = "backendA"
    }
    private var cb: CircuitBreaker? = factory.create(BACKEND)

    fun exec(function: () -> T, fallback: (exception: Throwable) -> T): T =
        when(cb) {
            null -> function()
            else -> cb!!.run(function, fallback)
        }
}