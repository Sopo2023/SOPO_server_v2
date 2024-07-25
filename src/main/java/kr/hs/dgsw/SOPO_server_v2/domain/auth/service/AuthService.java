package kr.hs.dgsw.SOPO_server_v2.domain.auth.service;

import kr.hs.dgsw.SOPO_server_v2.domain.auth.dto.req.SignUpReq;
import kr.hs.dgsw.SOPO_server_v2.domain.member.entity.MemberEntity;
import kr.hs.dgsw.SOPO_server_v2.domain.member.enums.MemberCategory;
import kr.hs.dgsw.SOPO_server_v2.domain.member.enums.MemberState;
import kr.hs.dgsw.SOPO_server_v2.domain.member.repository.MemberRepository;
import kr.hs.dgsw.SOPO_server_v2.global.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class AuthService {
    private final AuthEmailService authEmailService;
    private final MemberRepository memberRepository;

    @Transactional(rollbackFor = Exception.class)
    public Response signUp(SignUpReq signUpReq){
        authEmailService.verifiedCode(signUpReq.memberEmail(), signUpReq.authCode());

        MemberEntity memberEntity = memberRepository.save(MemberEntity
                .builder()
                        .memberId(signUpReq.memberId())
                        .memberName(signUpReq.memberName())
                        .memberEmail(signUpReq.memberEmail())
                        .memberPassword(new BCryptPasswordEncoder().encode(signUpReq.memberPassword()))
                        .memberSchool(signUpReq.School())
                        .memberCategory(MemberCategory.USER)
                        .memberState(MemberState.ACTIVE)
                        .memberFcmToken(signUpReq.memberFcmToken())
                        .isOffAlarm(Boolean.TRUE)
                        .memberProfile(null)
                .build());

        return Response.of(HttpStatus.OK, "회원가입 성공");
    }
}
