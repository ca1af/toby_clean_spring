package com.calaf.cleanspring.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class MemberTest : FreeSpec({
    lateinit var member: Member

    beforeTest {
        member = Member(
            nickname = "A",
            email = "B",
            passwordHash = "C"
        )
    }

    "멤버를 생성하면 상태는 PENDING 이다" {
        member.getMemberStatus() shouldBe MemberStatus.PENDING
    }

    "activate() 함수를 호출하면 활성화된다" {
        member.activate()

        member.getMemberStatus() shouldBe MemberStatus.ACTIVE
    }

    "Pending 상태가 아닌 멤버를 activate() 하면 예외" - {
        "DEACTIVATED 상태인 경우" {
            member.activate()

            member.deactivate()

            shouldThrow<IllegalStateException> {
                member.activate()
            }
        }

        "ACTIVE 상태인 경우" {
            member.activate()

            shouldThrow<IllegalStateException> {
                member.activate()
            }
        }
    }

    "Active 상태가 아닌 멤버를 deactivate() 하면 예외" - {
        "PENDING 상태인 경우" {
            shouldThrow<IllegalStateException> {
                member.deactivate()
            }
        }

        "DEACTIVATE 상태인 경우" {
            member.activate()

            member.deactivate()

            shouldThrow<IllegalStateException> {
                member.deactivate()
            }
        }
    }
})
