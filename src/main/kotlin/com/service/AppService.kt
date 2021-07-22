package com.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.model.Bids
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import mu.KotlinLogging
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.io.File
import javax.annotation.PostConstruct

@Service
class AppService(
    private val objectMapper: ObjectMapper
) {
    private val logger = KotlinLogging.logger {}

    @Scheduled(fixedRateString = "5000")
    fun scheduling() {
        logger.info("job is working now")
        val text = File("src/main/resources/bids.json").readText()
        val bids = objectMapper.readValue(text, Bids::class.java)
        bids.forEach {
            GlobalScope.async {
                println(it.bid.toString())
                Thread.sleep(2000)
                logger.info("coroutine stop")
            }
        }

    }

    @PostConstruct
    fun init() {
        logger.info("appService Created")
    }
}