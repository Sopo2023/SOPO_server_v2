package kr.hs.dgsw.SOPO_server_v2.domain.member.service;

import kr.hs.dgsw.SOPO_server_v2.domain.auth.service.AuthEmailService;
import kr.hs.dgsw.SOPO_server_v2.domain.member.entity.MemberEntity;
import kr.hs.dgsw.SOPO_server_v2.domain.member.presentation.dto.req.MemberModifyReq;
import kr.hs.dgsw.SOPO_server_v2.domain.member.presentation.dto.res.ReadProfileRes;
import kr.hs.dgsw.SOPO_server_v2.domain.member.repository.MemberRepository;
import kr.hs.dgsw.SOPO_server_v2.global.error.custom.email.CodeIsWrongException;
import kr.hs.dgsw.SOPO_server_v2.global.error.custom.member.NeedAuthCode;
import kr.hs.dgsw.SOPO_server_v2.global.infra.security.GetCurrentMember;
import kr.hs.dgsw.SOPO_server_v2.global.response.Response;
import kr.hs.dgsw.SOPO_server_v2.global.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class MemberProfileService {
    private final MemberRepository memberRepository;
    private final GetCurrentMember getCurrentMember;
    private final AuthEmailService authEmailService;

    @Transactional(rollbackFor = Exception.class)
    public Response memberModify(MemberModifyReq memberModifyReq) {
        MemberEntity member = memberRepository.findByMemberId(getCurrentMember.current().getMemberId());

        String memberEmail = memberModifyReq.memberEmail();
        String authCode = memberModifyReq.authCode();

        if (memberEmail != null && !memberEmail.isEmpty()) {
            if (authCode == null || authCode.isEmpty()) {
                throw NeedAuthCode.EXCEPTION;
            }
            if (!authEmailService.verifiedCode(memberEmail, authCode)) {
                throw CodeIsWrongException.EXCEPTION;
            }
            member.setMemberEmail(memberEmail);
        }

        String memberPassword = memberModifyReq.memberPassword();
        if (memberPassword != null) {
            member.setMemberPassword(new BCryptPasswordEncoder().encode(memberPassword));
        }

        String memberName = memberModifyReq.memberName();
        if (memberName != null) {
            member.setMemberName(memberName);
        }

        memberRepository.save(member);

        return Response.of(HttpStatus.OK, "성공");
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseData<ReadProfileRes> loadProfile(){
        MemberEntity member = getCurrentMember.current();

        return ResponseData.of(HttpStatus.OK, "조회 성공", ReadProfileRes.of(memberRepository.findByMemberId(member.getMemberId())));
    }
}
