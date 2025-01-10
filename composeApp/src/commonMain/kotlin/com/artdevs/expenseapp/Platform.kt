package com.artdevs.expenseapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform