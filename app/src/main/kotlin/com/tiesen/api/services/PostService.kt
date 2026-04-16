package com.tiesen.api.services

import com.tiesen.api.dtos.PostDto
import com.tiesen.api.models.PostModel

class PostService {
  private val posts = mutableListOf<PostModel>()

  fun all(): List<PostModel> {
    return posts
  }

  fun one(id: Int): PostModel? {
    return posts.find { it.id == id }
  }

  fun create(post: PostDto): PostModel {
    val newPost = PostModel(posts.size + 1, post.title, post.content)
    posts.add(newPost)
    return newPost
  }

  fun update(id: Int, post: PostDto): PostModel? {
    val existingPost = posts.find { it.id == id } ?: return null
    val updatedPost = existingPost.copy(title = post.title, content = post.content)
    posts[posts.indexOf(existingPost)] = updatedPost
    return updatedPost
  }

  fun delete(id: Int): Boolean {
    return posts.removeIf { it.id == id }
  }
}
