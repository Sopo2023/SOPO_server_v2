package kr.hs.dgsw.SOPO_server_v2.domain.auth.presentation.dto.res;

import lombok.Builder;

@Builder
public record TokenRes(
        String accessToken,
        String refreshToken
) {
}
