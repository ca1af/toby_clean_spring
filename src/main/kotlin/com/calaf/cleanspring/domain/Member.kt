package com.calaf.cleanspring.domain

import jakarta.persistence.*

@Entity
@Access(AccessType.FIELD)
class Member private constructor(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    @field:Column(name = "member_id", nullable = false, updatable = false)
    private var id: Long? = null,
    private var email: String,
    private var nickname: String,
    private var passwordHash: String,
) {
    companion object {
        fun create(email: String, nickname: String, password: String, passwordEncoder: PasswordEncoder): Member {
            return Member(email = email, nickname = nickname, passwordHash = passwordEncoder.encode(password))
        }
    }

    @field:Enumerated(EnumType.STRING)
    @field:Column(name = "member_status", nullable = false)
    private var memberStatus: MemberStatus = MemberStatus.PENDING

    fun activate() {
        check(memberStatus == MemberStatus.PENDING) { "Member already activated." }

        memberStatus = MemberStatus.ACTIVE
    }

    fun deactivate() {
        check(memberStatus == MemberStatus.ACTIVE) { "Member already deactivated." }

        memberStatus = MemberStatus.DEACTIVATED
    }

    fun verifyPassword(password: String, passwordEncoder: PasswordEncoder): Boolean {
        return passwordEncoder.matches(password, passwordHash)
    }

    fun changeNickname(newNickname: String) {
        nickname = newNickname
    }

    fun changePassword(newPassword: String, passwordEncoder: PasswordEncoder) {
        passwordHash = passwordEncoder.encode(newPassword)
    }

    fun getMemberStatus(): MemberStatus {
        return memberStatus
    }

    fun getNickname(): String {
        return nickname
    }
}