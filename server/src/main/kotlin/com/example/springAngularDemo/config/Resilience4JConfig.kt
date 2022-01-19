package com.example.springAngularDemo.config

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig
import io.github.resilience4j.timelimiter.TimeLimiterConfig
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder
import org.springframework.cloud.client.circuitbreaker.Customizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Duration
import java.time.temporal.ChronoUnit

@Configuration
class Resilience4JConfig {

//    @Bean
//    fun circuitBreakerConfig(): CircuitBreakerConfig = CircuitBreakerConfig.custom()
//        .failureRateThreshold(1F)
//        .ringBufferSizeInClosedState(2) //minimum number of call attempt
//        .ringBufferSizeInHalfOpenState(1)
//        .recordFailure{ throw InvalidApplicationException("error") }
//        .build()
//    fun customizer(): CircuitBreakerConfigCustomizer =
//        CircuitBreakerConfigCustomizer.of("backendA") {
//            it.slidingWindow(100, 1, CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
//                .minimumNumberOfCalls(5)
//                .build()
//        }

    @Bean
    fun configCircuitBreaker(): Customizer<Resilience4JCircuitBreakerFactory> =
        Customizer { resilience ->
            resilience.configureDefault {
                Resilience4JConfigBuilder(it)
                    .circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
                    .timeLimiterConfig(
                        TimeLimiterConfig.custom().timeoutDuration(
                            Duration.of(
                                3,
                                ChronoUnit.SECONDS
                            )
                        ).build()
                    )
                    .build()
            }
        }
}