package kr.hs.dgsw.SOPO_server_v2.global.infra.cloud.storagy;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import jakarta.transaction.Transactional;
import kr.hs.dgsw.SOPO_server_v2.domain.file.dto.FileRes;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class S3Uploader {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3 amazonS3;


    public List<FileRes> uploadList(List<MultipartFile> fileList) {
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
            }

        } catch (IOException e) {
            throw new RuntimeException("Error uploading file to S3", e);
        }
        return fileLists;
    }
}