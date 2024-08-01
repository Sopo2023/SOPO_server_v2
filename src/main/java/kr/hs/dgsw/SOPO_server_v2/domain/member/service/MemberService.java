package kr.hs.dgsw.SOPO_server_v2.domain.member.service;

import kr.hs.dgsw.SOPO_server_v2.domain.member.entity.MemberEntity;
import kr.hs.dgsw.SOPO_server_v2.domain.member.enums.MemberState;
import kr.hs.dgsw.SOPO_server_v2.domain.member.repository.MemberRepository;
import kr.hs.dgsw.SOPO_server_v2.global.infra.security.GetCurrentMember;
import kr.hs.dgsw.SOPO_server_v2.global.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final GetCurrentMember getCurrentMember;
    @Transactional(rollbackFor = Exception.class)
    public Response deleteMember(){
        MemberEntity member = getCurrentMember.current();
        member.setMemberState(MemberState.DELETED);
        return Response.of(HttpStatus.OK, "성공");
    }
}

