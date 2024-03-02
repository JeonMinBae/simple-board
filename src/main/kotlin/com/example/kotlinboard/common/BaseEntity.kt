package com.example.kotlinboard.common

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.jpa.domain.support.AuditingEntityListener

@EntityListeners(AuditingEntityListener::class)
@MappedSuperclass
abstract class BaseEntity: BaseTimeEntity(){
    @Column(name = "register_user", updatable = false)
    @CreatedBy
    var registerUser: String? = null
        private set

    @Column(name = "update_user")
    @LastModifiedBy
    var updateUser: String? = null
}