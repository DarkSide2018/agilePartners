package com.service

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CommonBeans {

    @Bean
    fun objectMapper(): ObjectMapper {
        return ObjectMapper()
    }
}