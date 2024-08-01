package kr.hs.dgsw.SOPO_server_v2.domain.board.repository;

import kr.hs.dgsw.SOPO_server_v2.domain.board.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
}
