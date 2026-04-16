package com.tiesen.api.controllers

import com.tiesen.api.services.HomeService
import com.tiesen.api.utils.Response
import java.lang.management.ManagementFactory
import kotlin.math.round
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class HomeController(private val homeService: HomeService) {
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

  @GetMapping("/health")
  @ResponseBody
  fun health(): ResponseEntity<Response<Map<String, Any>>> {
    val runtimeMXBean = ManagementFactory.getRuntimeMXBean()
    val uptime = runtimeMXBean.uptime / 1000.0

    val runtime = Runtime.getRuntime()
    val mb = 1024.0 * 1024.0
    val totalMemory = runtime.totalMemory() / mb
    val freeMemory = runtime.freeMemory() / mb
    val usedMemory = totalMemory - freeMemory

    val version =
        try {
          val file =
              java.io.File("package.json").takeIf { it.exists() } ?: java.io.File("../package.json")
          if (file.exists()) {
            """"version"\s*:\s*"([^"]+)"""".toRegex().find(file.readText())?.groupValues?.get(1)
                ?: "1.0.0"
          } else {
            "1.0.0"
          }
        } catch (e: Exception) {
          "1.0.0"
        }

    val data =
        mapOf(
            "version" to version,
            "status" to "healthy",
            "uptime" to round(uptime * 1000) / 1000.0,
            "memory" to
                mapOf(
                    "used" to round(usedMemory * 100) / 100.0,
                    "total" to round(totalMemory * 100) / 100.0,
                    "unit" to "MB",
                ),
        )

    return Response.ok("Service is healthy", data)
  }
}
