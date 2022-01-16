package com.example.springAngularDemo

import com.example.springAngularDemo.book.BookEntity
import com.example.springAngularDemo.book.BookRepository
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.util.*

@SpringBootApplication
class SpringAngularDemoApplication {

    @Bean
    fun populate(repo: BookRepository): ApplicationRunner = ApplicationRunner {
        listOf(
            BookEntity(UUID.randomUUID(), "title 1", "author 1"),
            BookEntity(UUID.randomUUID(), "title 2", "author 2"),
            BookEntity(UUID.randomUUID(), "title 3", "author 3"),
            BookEntity(UUID.randomUUID(), "title 4", "author 4"),
            BookEntity(UUID.randomUUID(), "title 5", "author 5")
        ).forEach {
            repo.save(it)
        }
        repo.findAll().forEach(::println)
    }
}

fun main(args: Array<String>) {
    runApplication<SpringAngularDemoApplication>(*args)
}

