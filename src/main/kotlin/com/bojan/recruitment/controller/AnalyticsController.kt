package com.bojan.recruitment.controller

import com.bojan.recruitment.service.AnalyticsService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/analytics")
class AnalyticsController(
    private val analyticsService: AnalyticsService
) {

    @GetMapping
    fun getAnalytics() = analyticsService.getAnalytics()
}
