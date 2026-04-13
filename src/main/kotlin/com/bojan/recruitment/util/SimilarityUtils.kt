package com.bojan.recruitment.util

import kotlin.math.sqrt

object SimilarityUtils {

    fun cosineSimilarity(a: FloatArray, b: FloatArray): Double {
        val dot = a.zip(b).sumOf { (x, y) -> (x * y).toDouble() }
        val normA = sqrt(a.sumOf { (it * it).toDouble() })
        val normB = sqrt(b.sumOf { (it * it).toDouble() })

        return if (normA == 0.0 || normB == 0.0) 0.0
        else dot / (normA * normB)
    }
}
