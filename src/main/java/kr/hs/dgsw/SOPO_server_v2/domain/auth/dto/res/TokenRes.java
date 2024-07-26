package kr.hs.dgsw.SOPO_server_v2.domain.auth.dto.res;

import lombok.Builder;

@Builder
public record TokenRes(
        String accessToken,
        String refreshToken
) {
}
