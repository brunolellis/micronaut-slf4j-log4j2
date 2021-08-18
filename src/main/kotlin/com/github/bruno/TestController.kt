package com.github.bruno

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import org.slf4j.LoggerFactory
import java.time.Instant

@Controller
class TestController {

    companion object {
        private val LOG = LoggerFactory.getLogger(TestController::class.java)
    }

    @Get("/")
    fun test(): Test {
        LOG.info("request received on '/'")

        return Test(Instant.now())
    }
}

data class Test(private val now: Instant)