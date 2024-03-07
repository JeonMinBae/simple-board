package com.example.kotlinboard.file

import com.example.kotlinboard.common.BaseTimeEntity
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.UUID

@Entity
class File(
    val convertedName: String,
    val originalName: String,
    val ext: String,
    val contentType: String,
    val path: String,
    val size: Long,
    @Id
    val id: String = UUID.randomUUID().toString()
): BaseTimeEntity() {

}