package kr.hs.dgsw.SOPO_server_v2.global.infra.jwt;

import ch.qos.logback.core.subst.Token;
import kr.hs.dgsw.SOPO_server_v2.domain.member.repository.MemberRepository;
import kr.hs.dgsw.SOPO_server_v2.global.error.custom.auth.WithdrawalMemberException;
import kr.hs.dgsw.SOPO_server_v2.global.properties.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtProvider {
    private final JwtProperties jwtProperties;
    private final MemberRepository memberRepository;

//    public Token generateToken(Long id, final MemberRole role) {
//        if(memberRepository.findById(id).memberState()== MemberState.DELETED){
//            throw WithdrawalMemberException.EXCEPTION;
//        }
//        return new Token(generateAccessToken(id,role),generateRefreshToken(id,role));
//    }
}
