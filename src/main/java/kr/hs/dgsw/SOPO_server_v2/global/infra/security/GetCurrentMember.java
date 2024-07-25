package kr.hs.dgsw.SOPO_server_v2.global.infra.security;

import kr.hs.dgsw.SOPO_server_v2.domain.member.entity.MemberEntity;
import org.springframework.security.core.context.SecurityContextHolder;

public class GetCurrentMember {
    public MemberEntity current() {
        return ((CustomMemberDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).member();
    }
}
