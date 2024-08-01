package kr.hs.dgsw.SOPO_server_v2.domain.member.presentation.dto.res;

import kr.hs.dgsw.SOPO_server_v2.domain.member.entity.MemberEntity;

public record LoadProfileRes(
        String memberId,
        String memberName,
        String memberEmail,
        String memberSchool
        ) {

        public static LoadProfileRes of(MemberEntity member){
                return new LoadProfileRes(
                        member.getMemberId(),
                        member.getMemberName(),
                        member.getMemberEmail(),
                        member.getMemberSchool()
                );

        }
}
