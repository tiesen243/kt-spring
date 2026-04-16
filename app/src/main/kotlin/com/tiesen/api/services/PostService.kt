package com.tiesen.api.services

import com.tiesen.api.dtos.PostDTO
import com.tiesen.api.models.PostModel
import com.tiesen.api.repositories.PostRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class PostService(private val postRepository: PostRepository) {
  fun all(): List<PostModel> {
    return postRepository.findAll().toList()
  }

  fun one(id: Int): PostModel? {
    return postRepository.findByIdOrNull(id)
  }

  fun create(post: PostDTO): PostModel {
    val newPost = PostModel(title = post.title, content = post.content)
    return postRepository.save(newPost)
  }

  fun update(id: Int, post: PostDTO): PostModel? {
    val existingPost = postRepository.findByIdOrNull(id) ?: return null
    val updatedPost = existingPost.copy(title = post.title, content = post.content)
    return postRepository.save(updatedPost)
  }

  fun delete(id: Int): Boolean {
    if (!postRepository.existsById(id)) return false
    postRepository.deleteById(id)
    return true
  }
}
