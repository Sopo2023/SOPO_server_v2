package kr.hs.dgsw.SOPO_server_v2.domain.contest.dto;

import kr.hs.dgsw.SOPO_server_v2.domain.contest.entity.ContestEntity;

import java.time.LocalDateTime;

public record ContestLoadRes (
        Long contestId,
        String contestTitle,
        String contestContent,
        Integer contestMax,
        Integer contestPerson,
        LocalDateTime contestDateTime
        // List<String> fileUrls,
        // Long memberId
) {
    public static ContestLoadRes of(ContestEntity contest) {
        return new ContestLoadRes (
                contest.getContestId(),
                contest.getContestTitle(),
                contest.getContestContent(),
                contest.getContestMax(),
                contest.getContestPerson(),
                contest.getContestDateTime()
                //contest.getFile() Url을 String 으로 묶어서 받아야 함.
        );
    }
}
