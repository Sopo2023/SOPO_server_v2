package kr.hs.dgsw.SOPO_server_v2.global.common.aws;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import kr.hs.dgsw.SOPO_server_v2.global.common.aws.dto.S3Res;
import kr.hs.dgsw.SOPO_server_v2.global.config.S3Config;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service {

    private final AmazonS3 amazonS3;
    private final S3Config s3Config;

    public S3Res upload(MultipartFile file, String dirName){
        String originFileName = file.getOriginalFilename();

        String uuid = UUID.randomUUID().toString();
        assert originFileName != null;
        String uniqueFileName = uuid + "_" + originFileName.replaceAll("\\s", "_");

        String fileName = dirName + "/" + uniqueFileName;

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(file.getSize());
        objectMetadata.setContentType(file.getContentType());

        try(InputStream inputStream = file.getInputStream()) {
            amazonS3.putObject(new PutObjectRequest(s3Config.bucket, fileName, inputStream, objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch(IOException e) {
            throw new IllegalArgumentException(String.format("파일을 올리는데 실패했습니다. %s", fileName));
        }
        String url = amazonS3.getUrl(s3Config.bucket, fileName).toString();
        return S3Res.of(url);
    }

    public void deleteFile(String fileName) {
        String decodedFileName = URLDecoder.decode(fileName, StandardCharsets.UTF_8);
        amazonS3.deleteObject(s3Config.bucket, decodedFileName);
    }

    public S3Res updateFile(MultipartFile newFile, String oldFileName, String dirName) throws IOException {
        deleteFile(oldFileName);
        return upload(newFile, dirName);
    }

}
