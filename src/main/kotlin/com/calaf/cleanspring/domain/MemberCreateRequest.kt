package com.calaf.cleanspring.domain

data class MemberCreateRequest(
    val email: String,
    val nickname: String,
    val password: String,
)