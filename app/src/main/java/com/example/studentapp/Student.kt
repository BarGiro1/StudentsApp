package com.example.studentapp

data class Student(
    var id: String,
    var name: String,
    val phoneNumber: String,
    val address: String,
    var isChecked: Boolean = false,
    var imageUrl: String = "default_avatar"
)