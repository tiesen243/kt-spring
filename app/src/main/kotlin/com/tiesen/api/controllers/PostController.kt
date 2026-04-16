package com.tiesen.api.controllers

import com.tiesen.api.dtos.PostDto
import com.tiesen.api.services.PostService
import com.tiesen.api.utils.Response
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
class PostController {
  private val postService = PostService()

  @GetMapping("/api/v1/posts") fun all() = Response.ok("All posts retrieved", postService.all())

  @GetMapping("/api/v1/posts/{id}")
  fun one(@PathVariable id: Int) =
      postService.one(id)?.let { Response.ok("Post with id $id retrieved", it) }
          ?: Response.error(HttpStatus.NOT_FOUND, "Post with id $id not found")

  @PostMapping("/api/v1/posts")
  fun create(@RequestBody post: PostDto) =
      try {
        Response.created("Post created successfully", postService.create(post))
      } catch (e: Exception) {
        Response.error(HttpStatus.INTERNAL_SERVER_ERROR, e.message ?: "An error occurred")
      }

  @PutMapping("/api/v1/posts/{id}")
  fun update(@PathVariable id: Int, @RequestBody post: PostDto) =
      try {
        postService.update(id, post)?.let { Response.ok("Post with id $id updated", it) }
            ?: Response.error(HttpStatus.NOT_FOUND, "Post with id $id not found")
      } catch (e: Exception) {
        Response.error(HttpStatus.INTERNAL_SERVER_ERROR, e.message ?: "An error occurred")
      }

  @DeleteMapping("/api/v1/posts/{id}")
  fun delete(@PathVariable id: Int) =
      try {
        postService.delete(id).let {
          if (it) Response.ok("Post with id $id deleted")
          else Response.error(HttpStatus.NOT_FOUND, "Post with id $id not found")
        }
      } catch (e: Exception) {
        Response.error(HttpStatus.INTERNAL_SERVER_ERROR, e.message ?: "An error occurred")
      }
}
