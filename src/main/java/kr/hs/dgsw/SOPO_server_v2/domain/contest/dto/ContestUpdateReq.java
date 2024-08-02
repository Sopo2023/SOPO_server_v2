package kr.hs.dgsw.SOPO_server_v2.domain.contest.dto;

import java.time.LocalDateTime;

public record ContestUpdateReq (
        String contestTitle,
        String contestContent,
        Integer contestMax,
        Integer contestPerson,
        LocalDateTime contestDateTime
) {
}
