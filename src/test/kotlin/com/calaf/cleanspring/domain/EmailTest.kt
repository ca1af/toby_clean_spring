package com.calaf.cleanspring.domain

import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class EmailTest : FreeSpec({
    "동등성 비교" {
        val email1 = Email("poby@vl.com")
        val email2 = Email("poby@vl.com")

        assertSoftly {
            email2.address shouldBe email1.address
            email1 shouldBe email2
        }
    }
})
