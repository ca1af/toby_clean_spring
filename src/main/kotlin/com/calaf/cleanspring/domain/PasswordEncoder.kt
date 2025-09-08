package com.calaf.cleanspring.domain

interface PasswordEncoder {
    fun encode(password: String): String
    fun matches(password: String, encodedPassword: String): Boolean
}