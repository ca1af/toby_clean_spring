package com.calaf.cleanspring.domain

import jakarta.persistence.*

@Entity
@Access(AccessType.FIELD)
class Member(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    @field:Column(name = "member_id", nullable = false, updatable = false)
    private var id: Long? = null,
    private var email: String,
    private var nickname: String,
    private var passwordHash: String,
) {
    @field:Enumerated(EnumType.STRING)
    @field:Column(name = "member_status", nullable = false)
    private var memberStatus: MemberStatus = MemberStatus.PENDING

    fun getId(): Long? {
        return id
    }

    fun getMemberStatus(): MemberStatus {
        return memberStatus
    }

    fun activate() {
        check(memberStatus == MemberStatus.PENDING) { "Member already activated."}

        memberStatus = MemberStatus.ACTIVE
    }

    fun deactivate() {
        check(memberStatus == MemberStatus.ACTIVE) { "Member already deactivated."}

        memberStatus = MemberStatus.DEACTIVATED
    }
}