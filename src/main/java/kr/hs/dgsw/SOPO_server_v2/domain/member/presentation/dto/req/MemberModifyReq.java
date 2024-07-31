package kr.hs.dgsw.SOPO_server_v2.domain.member.presentation.dto.req;

import jakarta.validation.constraints.Email;

public record MemberModifyReq(
        String memberName,
        @Email
        String memberEmail,
        String memberPassword
) {
}
