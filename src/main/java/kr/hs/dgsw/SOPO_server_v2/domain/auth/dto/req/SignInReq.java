package kr.hs.dgsw.SOPO_server_v2.domain.auth.dto.req;

public record SignInReq(
        String memberId,
        String memberPassword
) {
}
