package com.calaf.cleanspring.application.provided

import com.calaf.cleanspring.domain.Member
import com.calaf.cleanspring.domain.MemberRegisterRequest

/**
 * 회원의 등록과 관련된 기능을 제공한다
 */
fun interface MemberRegister {
    fun register(memberRegisterRequest: MemberRegisterRequest) : Member // 방향이 깨지지 않으므로 큰 문제는 없다 - 느슨한 레이어드
}
