package com.tiesen.api.controllers

import com.tiesen.api.services.HomeService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HomeController {
  private val homeService = HomeService()

  @GetMapping("/")
  fun index(model: Model): String {
    model.addAttribute("message", homeService.getHomeMessage())
    return "pages/index"
  }

  @GetMapping("/about")
  fun about(model: Model): String {
    model.addAttribute("message", "This is the about page.")
    return "pages/about"
  }
}
