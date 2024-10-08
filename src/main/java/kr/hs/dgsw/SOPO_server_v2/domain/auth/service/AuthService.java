package kr.hs.dgsw.SOPO_server_v2.domain.auth.service;

import kr.hs.dgsw.SOPO_server_v2.domain.auth.presentation.dto.req.SignInReq;
import kr.hs.dgsw.SOPO_server_v2.domain.auth.presentation.dto.req.SignUpReq;
import kr.hs.dgsw.SOPO_server_v2.domain.auth.presentation.dto.res.TokenRes;
import kr.hs.dgsw.SOPO_server_v2.domain.member.entity.MemberEntity;
import kr.hs.dgsw.SOPO_server_v2.domain.member.enums.MemberCategory;
import kr.hs.dgsw.SOPO_server_v2.domain.member.enums.MemberState;
import kr.hs.dgsw.SOPO_server_v2.domain.member.repository.MemberRepository;
import kr.hs.dgsw.SOPO_server_v2.global.error.custom.auth.WrongPasswordException;
import kr.hs.dgsw.SOPO_server_v2.global.error.custom.email.CodeIsWrongException;
import kr.hs.dgsw.SOPO_server_v2.global.error.custom.email.EmailAlreadyExistsException;
import kr.hs.dgsw.SOPO_server_v2.global.error.custom.member.IdAlreadyExistException;
import kr.hs.dgsw.SOPO_server_v2.global.error.custom.member.MemberNotFoundException;
import kr.hs.dgsw.SOPO_server_v2.global.infra.jwt.JwtProvider;
import kr.hs.dgsw.SOPO_server_v2.global.response.Response;
import kr.hs.dgsw.SOPO_server_v2.global.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class AuthService {
    private final JwtProvider jwtProvider;
    private final AuthEmailService authEmailService;
    private final MemberRepository memberRepository;

    @Transactional(rollbackFor = Exception.class)
    public Response signUp(SignUpReq signUpReq) {
        if (memberRepository.existsByMemberEmail(signUpReq.memberEmail())){
            throw EmailAlreadyExistsException.EXCEPTION;
        }

        if (memberRepository.existsByMemberId(signUpReq.memberId()))
            throw IdAlreadyExistException.EXCEPTION;

        if (!authEmailService.verifiedCode(signUpReq.memberEmail(), signUpReq.authCode()))
            throw CodeIsWrongException.EXCEPTION;

        memberRepository.save(MemberEntity
                .builder()
                .memberId(signUpReq.memberId())
                .memberName(signUpReq.memberName())
                .memberEmail(signUpReq.memberEmail())
                .memberPassword(new BCryptPasswordEncoder().encode(signUpReq.memberPassword()))
                .memberSchool(signUpReq.memberSchool())
                .memberCategory(MemberCategory.USER)
                .memberFcmToken(signUpReq.memberFcmToken())
                .memberProfile(null)
                .build());
        return Response.of(HttpStatus.OK, "회원가입 성공");
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseData<TokenRes> signIn(SignInReq signInReq){
        MemberEntity memberEntity = memberRepository.findByMemberId(signInReq.memberId());

        if(memberEntity == null || memberEntity.getMemberState().equals(MemberState.DELETED)) {
            throw MemberNotFoundException.EXCEPTION;
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(signInReq.memberPassword(), memberEntity.getMemberPassword())) {
            throw WrongPasswordException.EXCEPTION;
        }

        return ResponseData.of(HttpStatus.OK, "로그인 성공", jwtProvider.generateToken(signInReq.memberId(), memberEntity.getMemberState()));
    }

}
