package kr.hs.dgsw.SOPO_server_v2.global.infra.security;

import kr.hs.dgsw.SOPO_server_v2.domain.auth.dto.res.TokenRes;
import kr.hs.dgsw.SOPO_server_v2.domain.member.entity.MemberEntity;
import kr.hs.dgsw.SOPO_server_v2.domain.member.repository.MemberRepository;
import kr.hs.dgsw.SOPO_server_v2.global.infra.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberDetailsService {

    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;

    public TokenRes loadUserByUsername(String id) {
        MemberEntity memberEntity = memberRepository.findByMemberId(id);
        return jwtProvider.generateToken(id, memberEntity.getMemberState());
    }
}
