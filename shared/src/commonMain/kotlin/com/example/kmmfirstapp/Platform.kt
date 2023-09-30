package com.example.kmmfirstapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform