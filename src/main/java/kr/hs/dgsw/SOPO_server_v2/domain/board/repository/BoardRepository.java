package kr.hs.dgsw.SOPO_server_v2.domain.board.repository;

import kr.hs.dgsw.SOPO_server_v2.domain.board.entity.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    @NonNull
    Page<BoardEntity> findAll(@RequestParam @NonNull Pageable pageable);
}
