package kr.hs.dgsw.SOPO_server_v2.domain.file.repository;

import kr.hs.dgsw.SOPO_server_v2.domain.file.entity.FileEntity;
import kr.hs.dgsw.SOPO_server_v2.domain.file.enums.FileCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileRepository extends JpaRepository<FileEntity, Long> {
    List<FileEntity> findByBoard_BoardId(Long boardId);
    List<FileEntity> findByContest_ContestId(Long contestId);
    List<FileEntity> findByFileCategoryAndFkId(FileCategory fileCategory, String fkId);
}
