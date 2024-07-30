package kr.hs.dgsw.SOPO_server_v2.global.infra.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.UnsupportedJwtException;
import kr.hs.dgsw.SOPO_server_v2.domain.member.entity.MemberEntity;
import kr.hs.dgsw.SOPO_server_v2.domain.member.repository.MemberRepository;
import kr.hs.dgsw.SOPO_server_v2.global.error.custom.member.MemberNotFoundException;
import kr.hs.dgsw.SOPO_server_v2.global.infra.security.CustomMemberDetails;
import kr.hs.dgsw.SOPO_server_v2.global.properties.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Component
@RequiredArgsConstructor
public class JwtHelper {

    private final MemberRepository memberRepository;
    private final JwtProperties jwtProperties;

    @Transactional
    public Authentication getAuthentication(String accessToken) {
        Claims claims = getClaims(accessToken);
        MemberEntity member = memberRepository.findByMemberId(claims.getSubject());

        CustomMemberDetails details = new CustomMemberDetails(member);

        return new UsernamePasswordAuthenticationToken(details, null, details.getAuthorities());
    }

    public Claims getClaims(String token) {
        try{
        return Jwts.parserBuilder()
                .setSigningKey(jwtProperties.getSecretKey()).build().parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            throw new IllegalArgumentException("만료된 토큰");
        } catch (UnsupportedJwtException e) {
            throw new IllegalArgumentException("지원되지 않는 토큰");
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("잘못된 토큰");
        }
    }

    public String extractToken(final String token) {
        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return token;
    }

}
