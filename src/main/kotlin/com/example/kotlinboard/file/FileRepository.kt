package com.example.kotlinboard.file

import org.springframework.data.jpa.repository.JpaRepository

interface FileRepository : JpaRepository<File, String> {
}