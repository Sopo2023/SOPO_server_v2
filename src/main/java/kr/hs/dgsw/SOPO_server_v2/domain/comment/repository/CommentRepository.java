package kr.hs.dgsw.SOPO_server_v2.domain.comment.repository;

import kr.hs.dgsw.SOPO_server_v2.domain.comment.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
}
