package kr.hs.dgsw.SOPO_server_v2.domain.member.repository;

import kr.hs.dgsw.SOPO_server_v2.domain.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    MemberEntity findByMemberId(String memberId);
    boolean existsByMemberEmail(String email);
    boolean existsByMemberId(String memberId);

}
