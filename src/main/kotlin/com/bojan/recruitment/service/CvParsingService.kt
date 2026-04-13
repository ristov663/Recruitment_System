package com.bojan.recruitment.service

import java.io.File

interface CvParsingService {

    fun extractText(file: File): String
}
