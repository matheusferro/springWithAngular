package com.example.springAngularDemo.config

import java.util.function.Predicate

class PredicateTest : Predicate<Throwable> {

    override fun test(t: Throwable): Boolean =
        when (t) {
            is BusinessException -> {
                println("is BusinessException")
                true
            }
            else -> {
                println("Unrecognized exception $t")
                false
            }
        }
}

class BusinessException(message: String) : RuntimeException(message)