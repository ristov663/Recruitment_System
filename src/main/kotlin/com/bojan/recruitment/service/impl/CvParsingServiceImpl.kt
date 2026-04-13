package com.bojan.recruitment.service.impl

import com.bojan.recruitment.service.CvParsingService
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripper
import org.springframework.stereotype.Service
import java.io.File

@Service
class CvParsingServiceImpl : CvParsingService {

    override fun extractText(file: File): String {

        if (!file.exists()) {
            throw IllegalArgumentException("File does not exist")
        }

        PDDocument.load(file).use { document ->
            val stripper = PDFTextStripper()
            return stripper.getText(document)
        }
    }
}
