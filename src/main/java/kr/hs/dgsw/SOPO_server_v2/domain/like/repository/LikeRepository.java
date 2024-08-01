package kr.hs.dgsw.SOPO_server_v2.domain.like.repository;

import kr.hs.dgsw.SOPO_server_v2.domain.board.entity.BoardEntity;
import kr.hs.dgsw.SOPO_server_v2.domain.contest.entity.ContestEntity;
import kr.hs.dgsw.SOPO_server_v2.domain.like.entity.LikeEntity;
import kr.hs.dgsw.SOPO_server_v2.domain.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<LikeEntity, Long> {
    Optional<LikeEntity> findByMemberAndBoard(MemberEntity member, BoardEntity board);
    Optional<LikeEntity> findByMemberAndContest(MemberEntity member, ContestEntity contest);
}
