package kr.hs.dgsw.SOPO_server_v2.domain.comment.repository;

import kr.hs.dgsw.SOPO_server_v2.domain.board.entity.BoardEntity;
import kr.hs.dgsw.SOPO_server_v2.domain.comment.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> findByBoard(BoardEntity board);

    @Query("SELECT COUNT(c) FROM CommentEntity c WHERE c.board.boardId = :boardId")
    int countCommentsByBoardId(@Param("boardId") Long boardId);

    List<CommentEntity> findAllByBoardAndParentId(BoardEntity board, Long parentId);

    List<CommentEntity> findAllByBoard(BoardEntity board);
}
