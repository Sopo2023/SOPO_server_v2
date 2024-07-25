package kr.hs.dgsw.SOPO_server_v2.domain.auth.dto.res;

public record TokenRes(
        String accessToken,
        String refreshToken
) {
}
