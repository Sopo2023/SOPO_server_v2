package kr.hs.dgsw.SOPO_server_v2.domain.file.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import kr.hs.dgsw.SOPO_server_v2.domain.file.dto.FileRes;
import kr.hs.dgsw.SOPO_server_v2.domain.file.entity.FileEntity;
import kr.hs.dgsw.SOPO_server_v2.domain.file.enums.FileCategory;
import kr.hs.dgsw.SOPO_server_v2.domain.file.repository.FileRepository;
import kr.hs.dgsw.SOPO_server_v2.global.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileService {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3 amazonS3;

    private final FileRepository fileRepository;

    @Transactional
    public ResponseData<List<FileRes>> fileUpload(FileCategory fileCategory, List<MultipartFile> fileList) {
        List<FileRes> fileLists = new ArrayList<>();
        try {
            for (MultipartFile file : fileList) {
                String fileName = file.getOriginalFilename();

                ObjectMetadata metadata = new ObjectMetadata();
                metadata.setContentType(file.getContentType());
                metadata.setContentLength(file.getSize());

                // S3에 저장하는 코드
                amazonS3.putObject(
                        new PutObjectRequest(bucket, fileName, file.getInputStream(), metadata)
                );

                // FileRes 형식으로 만들기
                FileRes oneFile = FileRes.builder()
                        .fileName(fileName)
                        .fileUrl(amazonS3.getUrl(bucket, fileName).toString())
                        .build();


                fileLists.add(oneFile);

                // DB에 저장하는 코드
                FileEntity fileEntity = FileEntity.builder()
                        .fileName(fileName)
                        .fileCategory(fileCategory)
                        .fileUrl(amazonS3.getUrl(bucket, fileName).toString())
                        .build();
                fileRepository.save(fileEntity);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error uploading file to S3", e);
        }
        return ResponseData.of(HttpStatus.OK, "파일 업로드 완료", fileLists);
    }
}
