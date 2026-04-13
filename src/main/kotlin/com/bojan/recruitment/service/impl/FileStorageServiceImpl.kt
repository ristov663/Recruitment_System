package com.bojan.recruitment.service.impl

import com.bojan.recruitment.service.FileStorageService
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import java.util.*

@Service
class FileStorageServiceImpl : FileStorageService {

    private val uploadDir: Path = Paths.get("uploads")

    override fun saveFile(file: MultipartFile): String {

        if (!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir)
        }

        val fileName = UUID.randomUUID().toString() + "_" + file.originalFilename

        val filePath = uploadDir.resolve(fileName)

        Files.copy(file.inputStream, filePath, StandardCopyOption.REPLACE_EXISTING)

        return filePath.toString().replace("\\", "/")
    }

    override fun loadFile(path: String): ByteArray {
        val filePath = Paths.get(path)
        return Files.readAllBytes(filePath)
    }
}
