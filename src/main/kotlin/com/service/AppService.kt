package com.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.model.Bid
import com.model.Bids
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import mu.KotlinLogging
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.io.File
import java.util.*
import javax.annotation.PostConstruct

@Service
class AppService(
    private val objectMapper: ObjectMapper
) {
    private val logger = KotlinLogging.logger {}

    @Scheduled(fixedRateString = "5000")
    fun scheduling() {
        logger.info("job started")
        val text = File("src/main/resources/bids.json").readText()
        val bids = objectMapper.readValue(text, Bids::class.java)
        processMessage(bids)
        logger.info { "job finished" }
    }
    fun processMessage(bids: Bids) {
        bids.forEach {
            GlobalScope.async {
                it.bid?.let {
                    when (it.ty) {
                        "ZU" -> processZUType(it)
                        "AQ" -> processAQType(it)
                        else -> throw RuntimeException("unexpected type")
                    }
                }
            }
        }
    }

    suspend fun processAQType(bid: Bid) {
        logger.info("process AQ type started")
        val decode = Base64.getDecoder().decode(bid.pl)
        logger.info { "decoded AQ -> pl =  ${String(decode)} id=${bid.id} timestamp = ${bid.ts} " }
        logger.info("process AQ type completed")
    }

    suspend fun processZUType(bid: Bid) {
        logger.info("process ZU type started")
        val decode = Base64.getDecoder().decode(bid.pl)
        logger.info { "decoded ZU-> pl =  ${String(decode)} id=${bid.id} timestamp = ${bid.ts} " }
        logger.info("process ZU type completed")
    }

    @PostConstruct
    fun init() {
        logger.info("appService Created")
    }
}