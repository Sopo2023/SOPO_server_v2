package kr.hs.dgsw.SOPO_server_v2.domain.auth.dto.req;

import kr.hs.dgsw.SOPO_server_v2.domain.file.entity.FileEntity;
import kr.hs.dgsw.SOPO_server_v2.domain.member.enums.MemberCategory;
import kr.hs.dgsw.SOPO_server_v2.domain.member.enums.MemberState;

public record SignUpReq(
        String memberId,
        String memberName,
        String memberEmail,
        String memberPassword,
        String School,
        MemberCategory memberCategory,
        MemberState memberState,
        String memberFcmToken,
        FileEntity profile
) {
}
