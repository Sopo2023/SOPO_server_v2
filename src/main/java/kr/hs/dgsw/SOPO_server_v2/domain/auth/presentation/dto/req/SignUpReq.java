package kr.hs.dgsw.SOPO_server_v2.domain.auth.presentation.dto.req;

public record SignUpReq(
        String memberId,
        String memberName,
        String memberEmail,
        String authCode,
        String memberPassword,
        String memberSchool,
        String memberFcmToken
) {
}
