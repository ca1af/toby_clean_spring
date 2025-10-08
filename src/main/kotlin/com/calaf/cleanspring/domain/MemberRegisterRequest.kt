package com.calaf.cleanspring.domain

data class MemberRegisterRequest(
    val email: String,
    val nickname: String,
    val password: String,
)
