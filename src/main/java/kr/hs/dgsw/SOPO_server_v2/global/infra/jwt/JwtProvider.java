package kr.hs.dgsw.SOPO_server_v2.global.infra.jwt;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import kr.hs.dgsw.SOPO_server_v2.domain.auth.dto.res.TokenRes;
import kr.hs.dgsw.SOPO_server_v2.domain.member.enums.MemberState;
import kr.hs.dgsw.SOPO_server_v2.domain.member.repository.MemberRepository;
import kr.hs.dgsw.SOPO_server_v2.global.error.custom.auth.WithdrawalMemberException;
import kr.hs.dgsw.SOPO_server_v2.global.properties.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider {
    private final JwtProperties jwtProperties;
    private final MemberRepository memberRepository;

    public TokenRes generateToken(String id, final MemberState memberState) {
        if(memberRepository.findByMemberId(id).getMemberState()== MemberState.DELETED){
            throw WithdrawalMemberException.EXCEPTION;
        }
        return new TokenRes(
                generateAccessToken(id, memberState),
                generateRefreshToken(id, memberState));
    }


    public String generateAccessToken(String id, final MemberState memberState) {
        return Jwts.builder()
                .setHeaderParam(Header.JWT_TYPE, JwtType.ACCESS)
                .setSubject(id)
                .claim("Authorization", memberState)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getAccessExpire()))
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecretKey())
                .compact();
    }

    public String generateRefreshToken(String id, final MemberState memberState) {
        return Jwts.builder()
                .setHeaderParam(Header.JWT_TYPE, JwtType.REFRESH)
                .claim("Authorization", memberState)
                .setSubject(id.toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getRefreshExpire()))
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecretKey())
                .compact();
    }
}
