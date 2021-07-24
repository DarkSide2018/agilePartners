package com


import com.fasterxml.jackson.databind.ObjectMapper
import com.model.Bid
import com.model.Bids
import com.model.BidsItem
import com.service.AppService
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import kotlin.test.assertEquals


class TestCase {
    private val element1 = Bid("1", "1fjjfj", "1", "AQ")
    private val element2 = Bid("1", "1fkfkk", "1", "ZU")

    @Test
    fun testing() {
      val appService: AppService = mockService()
        val processMessage = appService.processMessage(mockBids())
        assertEquals(processMessage.size,2)
    }
    private fun mockService(): AppService {
        return AppService(objectMapper = ObjectMapper())
    }
    private fun <T> anyObject(): T {
        Mockito.anyObject<T>()
        return uninitialized()
    }

    private fun <T> uninitialized(): T = null as T
    fun mockBids(): Bids {
        val bids = Bids()
        bids.add(
            BidsItem(
                bid = element1
            )
        )
        bids.add(
            BidsItem(
                bid = element2
            )
        )
        return bids
    }
}