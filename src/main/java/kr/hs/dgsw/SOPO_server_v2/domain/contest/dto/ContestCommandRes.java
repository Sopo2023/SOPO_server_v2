package kr.hs.dgsw.SOPO_server_v2.domain.contest.dto;

import kr.hs.dgsw.SOPO_server_v2.domain.contest.entity.ContestEntity;


public record ContestCommandRes(
        Long contestId
) {
    public static ContestCommandRes of (ContestEntity contest) {
        return new ContestCommandRes(
                contest.getContestId()
        );
    }
}
