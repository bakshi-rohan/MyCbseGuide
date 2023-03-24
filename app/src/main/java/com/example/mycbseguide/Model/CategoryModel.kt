package com.example.mycbseguide.Model

data class Category(
    val children: List<Children>,
    val id: Int,
    val mobile_logo: String,
    val name: String,
    val parent: Any,
    val web_logo: String,
    val weight: Int
)
data class Children(
    val absolute_url_course: String,
    val id: Int,
    val mobile_logo: String,
    val name: String,
    val parent: Int,
    val web_logo: String,
    val weight: Int
)
