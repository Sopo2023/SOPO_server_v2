package kr.hs.dgsw.SOPO_server_v2.domain.file.repository;

import kr.hs.dgsw.SOPO_server_v2.domain.file.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileRepository extends JpaRepository<FileEntity, Long> {
    List<FileEntity> findByBoardBoardId(Long boardId);
    List<FileEntity> findByContestContestId(Long contestId);
}
