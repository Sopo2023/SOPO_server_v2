package kr.hs.dgsw.SOPO_server_v2.domain.auth.service;

import kr.hs.dgsw.SOPO_server_v2.domain.auth.dto.req.ReProvideTokenReq;
import kr.hs.dgsw.SOPO_server_v2.domain.auth.dto.res.ReProvideTokenRes;
import kr.hs.dgsw.SOPO_server_v2.domain.member.entity.MemberEntity;
import kr.hs.dgsw.SOPO_server_v2.domain.member.repository.MemberRepository;
import kr.hs.dgsw.SOPO_server_v2.global.infra.jwt.JwtProvider;
import kr.hs.dgsw.SOPO_server_v2.global.infra.jwt.ParseToken;
import kr.hs.dgsw.SOPO_server_v2.global.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class AuthTokenService {
    private final ParseToken parseToken;
    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;

    @Transactional(readOnly = true)
    public ResponseData<ReProvideTokenRes> reProvideToken(ReProvideTokenReq reProvideTokenReq){
        String id = parseToken.getSubjectFromRefreshToken(reProvideTokenReq.refreshToken());
        MemberEntity memberEntity = memberRepository.findByMemberId(id);
        String reissuedToken = jwtProvider.generateAccessToken(memberEntity.getMemberId(), memberEntity.getMemberState());
        return ResponseData.of(HttpStatus.OK, "토큰 재발급 성공", new ReProvideTokenRes(reissuedToken));
    }
}
