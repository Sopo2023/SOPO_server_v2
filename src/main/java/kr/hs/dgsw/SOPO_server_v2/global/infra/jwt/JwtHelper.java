package kr.hs.dgsw.SOPO_server_v2.global.infra.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
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
        MemberEntity member = memberRepository.findById(Long.valueOf(claims.getSubject()))
                .orElseThrow(()-> MemberNotFoundException.EXCEPTION);

        CustomMemberDetails details = new CustomMemberDetails(member);

        return new UsernamePasswordAuthenticationToken(details, null, details.getAuthorities());
    }

    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(jwtProperties.getSecretKey()).build().parseClaimsJws(token).getBody();
    }

    public String extractToken(final String token) {
        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return token;
    }

}
