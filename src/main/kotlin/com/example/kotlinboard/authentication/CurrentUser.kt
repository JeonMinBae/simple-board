package com.example.kotlinboard.authentication

import com.example.kotlinboard.user.User


object CurrentUser {
    private val currentUser = ThreadLocal.withInitial { User("", "", "") }

    fun set(user: User) {
        currentUser.set(user)
    }

    fun remove() {
        currentUser.remove()
    }

    val userId: String
        get() = currentUser.get().userId
    val username: String
        get() = currentUser.get().name

}