package com.calaf.cleanspring.domain

@JvmInline
value class Email(val address: String) {
    companion object {
        private val REGEX = Regex("^[A-Za-z](.*)(@)(.+)(\\.)(.+)")
    }

    init {
        require(address.matches(REGEX)) { "Invalid email format" }
    }
}