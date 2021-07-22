package com.service

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

@Service
class AppService {

    @Scheduled(fixedRateString = "5000")
    fun scheduling(){
        println("jobbing")
    }
    @PostConstruct
    fun init(){
        println("appService Created")
    }
}