package dev.hbrown.demo.domain

data class Contact(
    val id: Long? = null,
    val firstName: String,
    val lastName: String,
    val mobileNumber: String,
    val email: String,
)
