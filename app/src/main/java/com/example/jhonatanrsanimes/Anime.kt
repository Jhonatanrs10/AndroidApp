package com.example.jhonatanrsanimes

data class Anime(
    val name: String,
    val status: String,
    val release: String,
    val s1: String,
    val id: Int? = null,

    val onClick: ((Int?) -> Unit)? = null

)