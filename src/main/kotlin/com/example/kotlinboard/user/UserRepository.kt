package com.example.kotlinboard.user

import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
class UserRepository {

    companion object {
        private val userMap = mapOf(
            "user1@cawave.co.kr" to "qwe123!@#",
            "user2@cawave.co.kr" to "qwe123!@#",
            "user3@cawave.co.kr" to "qwe123!@#",
        )
    }

    fun findById(userId: String): Optional<User> {
        return if(userMap.containsKey(userId)) {
            Optional.of(User(userId, userMap.getOrDefault(userId, ""), userId.slice(0..4)))
        }else{
            Optional.empty()
        }
    }
}