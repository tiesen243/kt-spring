package com.tiesen.api.models

import jakarta.persistence.*

@Entity
@Table(name = "posts")
data class PostModel(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Int = 0,
    val title: String = "",
    val content: String = "",
)
