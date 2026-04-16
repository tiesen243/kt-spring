package com.tiesen.api.services

import org.springframework.stereotype.Service

@Service
class HomeService {
  fun getHomeMessage(): String {
    return "Welcome to the Home Service!"
  }
}
