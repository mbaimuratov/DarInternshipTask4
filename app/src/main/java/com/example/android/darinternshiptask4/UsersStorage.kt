package com.example.android.darinternshiptask4

import java.util.*

class UsersStorage {

    private val studentsSet = LinkedList<User>()

    companion object {
        private var instance: UsersStorage? = null
        fun getInstance(): UsersStorage? {
            if (instance == null) {
                synchronized(this) {
                    instance = UsersStorage()
                }
            }
            return instance
        }
    }

    fun addUser(user: User) {
        studentsSet.add(user)
    }

    fun hasUser(user: User) = studentsSet.contains(user)

    fun getUser(user: User) = studentsSet[studentsSet.indexOf(user)]
    fun getUsers(): List<User> {
        return studentsSet.toList()
    }
}