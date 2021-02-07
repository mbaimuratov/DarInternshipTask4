package com.example.android.darinternshiptask4

data class User(
    val name: String? = "UserName",
    var winCount: Int = 0,
    var loseCount: Int = 0
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null) return false
        if (javaClass != other.javaClass) return false
        val other: User = other as User
        if (name == null) {
            if (other.name != null) return false
        } else if (name != other.name) return false
        return true
    }

    override fun hashCode() = name.hashCode()
}
