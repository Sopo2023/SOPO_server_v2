package kr.hs.dgsw.SOPO_server_v2.domain.enroll.repository;

import kr.hs.dgsw.SOPO_server_v2.domain.contest.entity.ContestEntity;
import kr.hs.dgsw.SOPO_server_v2.domain.enroll.entity.EnrollEntity;
import kr.hs.dgsw.SOPO_server_v2.domain.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EnrollRepository extends JpaRepository<EnrollEntity, Long> {
    Optional<EnrollEntity> findByMemberAndContest(MemberEntity member, ContestEntity contest);
    List<EnrollEntity> findByContest(ContestEntity contest);
}