package kr.hs.dgsw.SOPO_server_v2.domain.contest.dto;

import kr.hs.dgsw.SOPO_server_v2.domain.contest.entity.ContestEntity;
import kr.hs.dgsw.SOPO_server_v2.domain.member.entity.MemberEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record ContestLoadRes (
        Long contestId,
        String contestTitle,
        String contestContent,
        Integer contestMax,
        Integer contestPerson,
        LocalDateTime contestDateTime,
        String memberName,
        List<String> memberIdList
) {
    public static ContestLoadRes of(ContestEntity contest) {
        List<String> memberIdList = contest.getMemberIdList().stream()
                .map(MemberEntity::getMemberId)
                .collect(Collectors.toList());

        // 대회 생성자의 이름 가져오기
        String memberName = contest.getMember().getMemberName();

        return new ContestLoadRes (
                contest.getContestId(),
                contest.getContestTitle(),
                contest.getContestContent(),
                contest.getContestMax(),
                contest.getContestPerson(),
                contest.getContestDateTime(),
                memberName,
                memberIdList
        );
    }
}
