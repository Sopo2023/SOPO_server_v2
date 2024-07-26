package kr.hs.dgsw.SOPO_server_v2.domain.file.repository;

import kr.hs.dgsw.SOPO_server_v2.domain.file.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileEntity, Long> {
}
