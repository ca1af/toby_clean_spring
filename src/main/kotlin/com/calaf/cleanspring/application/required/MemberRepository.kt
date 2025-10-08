package com.calaf.cleanspring.application.required

import com.calaf.cleanspring.domain.Member
import org.springframework.data.repository.Repository

interface MemberRepository: Repository<Member, Long> {
    fun save(member: Member): Member
}
