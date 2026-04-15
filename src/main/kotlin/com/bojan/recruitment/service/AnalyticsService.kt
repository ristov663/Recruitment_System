package com.bojan.recruitment.service

import com.bojan.recruitment.dto.analytics.AnalyticsDTO

interface AnalyticsService {

    fun getAnalytics(): AnalyticsDTO
}
