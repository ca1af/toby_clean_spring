package com.calaf.cleanspring.domain

import io.kotest.assertions.assertSoftly
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class MemberTest : FreeSpec({
    lateinit var member: Member

    val passwordEncoder: PasswordEncoder = object : PasswordEncoder {
        override fun encode(password: String): String {
            return password
        }

        override fun matches(password: String, encodedPassword: String): Boolean {
            return encode(password) == encodedPassword
        }
    }

    beforeTest {
        member = Member.create(
            MemberCreateRequest(
                nickname = "A",
                email = "email@github.com",
                password = "secret",
            ),
            passwordEncoder = passwordEncoder
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

    "비밀번호를 검증한다 " {
        assertSoftly {
            member.verifyPassword("secret", passwordEncoder) shouldBe true
            member.verifyPassword("hello", passwordEncoder) shouldBe false
        }
    }

    "멤버 닉네임 변경" {
        assertSoftly {
            member.getNickname() shouldBe "A"

            member.changeNickname("B")

            member.getNickname() shouldBe "B"
        }
    }

    "멤버 비밀번호 변경" {
        member.changePassword("<PASSWORD>", passwordEncoder)

        member.verifyPassword("<PASSWORD>", passwordEncoder) shouldBe true
    }

    "isActive 메서드는 멤버 활성화 상태를 반환한다" {
        member.isActive() shouldBe false

        member.activate()

        member.isActive() shouldBe true
    }

    "올바르지 않은 이메일은 예외" {
        shouldThrow<IllegalArgumentException> {
            Member.create(
                MemberCreateRequest(nickname = "A", email = "invalidemail.com", password = "secret"),
                passwordEncoder = passwordEncoder
            )
        }
    }
})
