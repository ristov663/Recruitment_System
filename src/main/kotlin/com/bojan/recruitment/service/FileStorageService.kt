package com.bojan.recruitment.service

import org.springframework.web.multipart.MultipartFile

interface FileStorageService {

    fun saveFile(file: MultipartFile): String
    fun loadFile(path: String): ByteArray
}
