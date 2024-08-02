package kr.hs.dgsw.SOPO_server_v2.domain.file.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import kr.hs.dgsw.SOPO_server_v2.domain.board.entity.BoardEntity;
import kr.hs.dgsw.SOPO_server_v2.domain.board.repository.BoardRepository;
import kr.hs.dgsw.SOPO_server_v2.domain.contest.entity.ContestEntity;
import kr.hs.dgsw.SOPO_server_v2.domain.contest.repository.ContestRepository;
import kr.hs.dgsw.SOPO_server_v2.domain.file.dto.FileRes;
import kr.hs.dgsw.SOPO_server_v2.domain.file.entity.FileEntity;
import kr.hs.dgsw.SOPO_server_v2.domain.file.enums.FileCategory;
import kr.hs.dgsw.SOPO_server_v2.domain.file.repository.FileRepository;
import kr.hs.dgsw.SOPO_server_v2.global.error.custom.board.BoardNotFound;
import kr.hs.dgsw.SOPO_server_v2.global.error.custom.contest.ContestNotFound;
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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FileService {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3 amazonS3;

    private final FileRepository fileRepository;

    private final BoardRepository boardRepository;

    private final ContestRepository contestRepository;

    @Transactional
    public ResponseData<List<FileRes>> fileUpload(Long id, FileCategory fileCategory, List<MultipartFile> fileList) {

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

                // 파일 업데이트 하는 코드
                if (fileCategory == FileCategory.BOARD) {

                    BoardEntity board = boardRepository.findById(id)
                            .orElseThrow(() -> BoardNotFound.EXCEPTION);

                    // DB에 저장하는 코드
                    FileEntity fileEntity = FileEntity.builder()
                            .fileName(fileName)
                            .fileCategory(fileCategory)
                            .fileUrl(amazonS3.getUrl(bucket, fileName).toString())
                            .board(board)
                            .build();
                    fileRepository.save(fileEntity);

                    // 현재 게시물의 파일 목록에 새로 업로드된 파일 추가
                    List<FileEntity> files = board.getFile();
                    if (files == null) {
                        files = new ArrayList<>();
                    }
                    files.add(fileEntity);
                    board.setFile(files); // BoardEntity의 파일 목록 직접 수정

                    // 게시물 엔티티를 DB에 저장하여 파일 목록 반영
                    boardRepository.save(board);

                } else if (fileCategory == FileCategory.CONTEST) {
                    ContestEntity contest = contestRepository.findById(id)
                            .orElseThrow(() -> ContestNotFound.EXCEPTION);

                    // DB에 저장하는 코드
                    FileEntity fileEntity = FileEntity.builder()
                            .fileName(fileName)
                            .fileCategory(fileCategory)
                            .fileUrl(amazonS3.getUrl(bucket, fileName).toString())
                            .contest(contest)
                            .build();

                    fileRepository.save(fileEntity);

                    // 현재 게시물의 파일 목록에 새로 업로드된 파일 추가
                    List<FileEntity> files = contest.getFile();
                    if (files == null) {
                        files = new ArrayList<>();
                    }
                    files.add(fileEntity);
                    contest.setFile(files); // BoardEntity의 파일 목록 직접 수정

                    // 게시물 엔티티를 DB에 저장하여 파일 목록 반영
                    contestRepository.save(contest);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error uploading file to S3", e);
        }
        return ResponseData.of(HttpStatus.OK, "파일 업로드 완료", fileLists);
    }

    public ResponseData<List<FileRes>> getFiles(Long id, FileCategory fileCategory) {

        List<FileRes> fileList = new ArrayList<>();

        if (fileCategory == FileCategory.BOARD) {

            fileList = fileRepository.findByBoard_BoardId(id).stream()
                    .map(file -> FileRes.builder()
                            .fileName(file.getFileName())
                            .fileUrl(file.getFileUrl())
                            .build())
                    .collect(Collectors.toList());

        } else if (fileCategory == FileCategory.CONTEST) {
            fileList = fileRepository.findByContest_ContestId(id).stream()
                    .map(file -> FileRes.builder()
                            .fileName(file.getFileName())
                            .fileUrl(file.getFileUrl())
                            .build())
                    .collect(Collectors.toList());
        }

        return ResponseData.of(HttpStatus.OK, "파일 조회 완료", fileList);
    }


}
