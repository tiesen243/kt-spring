package com.tiesen.api.repositories

import com.tiesen.api.models.PostModel
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository interface PostRepository : CrudRepository<PostModel, Int>
