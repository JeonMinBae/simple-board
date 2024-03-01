package com.example.kotlinboard.file

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.UUID

@Entity
class File(
    @Id
    val id: String = UUID.randomUUID().toString(),
    val convertedName: String,
    val originalName: String,
    val ext: String,
    val contentType: String,
    val path: String,
    val size: Long
) {

}