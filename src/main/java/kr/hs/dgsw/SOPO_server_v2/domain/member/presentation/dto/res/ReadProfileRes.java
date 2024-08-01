package kr.hs.dgsw.SOPO_server_v2.domain.member.presentation.dto.res;

import kr.hs.dgsw.SOPO_server_v2.domain.member.entity.MemberEntity;

public record ReadProfileRes(
        String memberId,
        String memberName,
        String memberEmail,
        String memberSchool
) {
    public static ReadProfileRes of(MemberEntity member){
        return new ReadProfileRes(
                member.getMemberId(),
                member.getMemberName(),
                member.getMemberEmail(),
                member.getMemberSchool()
        );
    }
}
