package com.github.bruno

import io.micronaut.runtime.Micronaut.build

fun main(args: Array<String>) {
	build()
	    .args(*args)
		.banner(false)
		.packages("com.github.bruno")
		.start()
}

