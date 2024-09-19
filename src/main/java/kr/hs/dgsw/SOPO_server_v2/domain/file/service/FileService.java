package kr.hs.dgsw.SOPO_server_v2.domain.file.service;

import kr.hs.dgsw.SOPO_server_v2.domain.file.dto.FileRes;
import kr.hs.dgsw.SOPO_server_v2.domain.file.entity.FileEntity;
import kr.hs.dgsw.SOPO_server_v2.domain.file.enums.FileCategory;
import kr.hs.dgsw.SOPO_server_v2.domain.file.repository.FileRepository;
import kr.hs.dgsw.SOPO_server_v2.global.infra.cloud.storagy.S3Uploader;
import kr.hs.dgsw.SOPO_server_v2.global.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileService {
    private final S3Uploader s3Uploader;
    private final FileRepository fileRepository;

    @Transactional
    public ResponseData<List<FileRes>> fileUpload(String id, FileCategory fileCategory, List<MultipartFile> fileList) {
        List<FileRes> fileRes = s3Uploader.uploadList(fileList);
        fileRepository.saveAll(fileRes.stream()
                .map(f -> FileEntity.builder()
                        .fileUrl(f.fileUrl())
                        .fileCategory(fileCategory)
                        .fileName(f.fileName())
                        .fkId(id)
                        .build())
                .toList());
        return ResponseData.of(HttpStatus.OK,"파일 업로드 성공",fileRes);
    }
}
