package kr.hs.dgsw.SOPO_server_v2.domain.comment.repository;


import kr.hs.dgsw.SOPO_server_v2.domain.comment.entity.SubCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCommentRepository extends JpaRepository<SubCommentEntity, Long> {
}
