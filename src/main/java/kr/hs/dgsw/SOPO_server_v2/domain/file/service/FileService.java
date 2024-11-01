package kr.hs.dgsw.SOPO_server_v2.domain.file.service;

import kr.hs.dgsw.SOPO_server_v2.domain.board.entity.BoardEntity;
import kr.hs.dgsw.SOPO_server_v2.domain.board.repository.BoardRepository;
import kr.hs.dgsw.SOPO_server_v2.domain.contest.entity.ContestEntity;
import kr.hs.dgsw.SOPO_server_v2.domain.contest.repository.ContestRepository;
import kr.hs.dgsw.SOPO_server_v2.domain.file.dto.FileRes;
import kr.hs.dgsw.SOPO_server_v2.domain.file.entity.FileEntity;
import kr.hs.dgsw.SOPO_server_v2.domain.file.enums.FileCategory;
import kr.hs.dgsw.SOPO_server_v2.domain.file.repository.FileRepository;
import kr.hs.dgsw.SOPO_server_v2.global.common.aws.S3Service;
import kr.hs.dgsw.SOPO_server_v2.global.common.aws.dto.S3Res;
import kr.hs.dgsw.SOPO_server_v2.global.error.custom.board.BoardNotFound;
import kr.hs.dgsw.SOPO_server_v2.global.error.custom.contest.ContestNotFound;
import kr.hs.dgsw.SOPO_server_v2.global.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FileService {

    private final S3Service s3Service;
    private final FileRepository fileRepository;
    private final BoardRepository boardRepository;
    private final ContestRepository contestRepository;

    @Transactional
    public ResponseData<List<FileRes>> fileUpload(Long id, FileCategory fileCategory, List<MultipartFile> fileList) {

        List<FileRes> fileRes = new ArrayList<>();
        for (MultipartFile file : fileList) {
            S3Res s3Res = s3Service.upload(file, fileCategory.toString());
            FileEntity fileEntity = createFileEntity(file, s3Res.url(), fileCategory);

            if (fileCategory == FileCategory.BOARD) {
                BoardEntity board = boardRepository.findById(id)
                        .orElseThrow(() -> BoardNotFound.EXCEPTION);
                saveFileEntity(fileEntity, board.getFile(), board, boardRepository::save);
            } else if (fileCategory == FileCategory.CONTEST) {
                ContestEntity contest = contestRepository.findById(id)
                        .orElseThrow(() -> ContestNotFound.EXCEPTION);
                saveFileEntity(fileEntity, contest.getFile(), contest, contestRepository::save);
            }
            fileRes.add(FileRes.of(fileEntity));
        }

        return ResponseData.of(HttpStatus.OK, "파일 업로드 완료", fileRes);
    }

    private FileEntity createFileEntity(MultipartFile file, String url, FileCategory fileCategory) {
        return FileEntity.builder()
                .fileName(file.getOriginalFilename())
                .fileCategory(fileCategory)
                .fileUrl(url)
                .build();
    }

    private <T> void saveFileEntity(FileEntity fileEntity, List<FileEntity> files, T entity, Consumer<T> saveFunction) {
        if (files == null) {
            files = new ArrayList<>();
        }
        files.add(fileEntity);

        if (entity instanceof BoardEntity) {
            fileEntity.setBoard((BoardEntity) entity);
            ((BoardEntity) entity).setFile(files);
        } else if (entity instanceof ContestEntity) {
            fileEntity.setContest((ContestEntity) entity);
            ((ContestEntity) entity).setFile(files);
        }

        fileRepository.save(fileEntity);
        saveFunction.accept(entity);
    }

    public ResponseData<List<FileRes>> getFiles(Long id, FileCategory fileCategory) {

        List<FileRes> fileList = new ArrayList<>();

        if (fileCategory == FileCategory.BOARD) {

            fileList = fileRepository.findByBoardBoardId(id).stream()
                    .map(FileRes::of)
                    .collect(Collectors.toList());

        } else if (fileCategory == FileCategory.CONTEST) {
            fileList = fileRepository.findByContestContestId(id).stream()
                    .map(FileRes::of)
                    .collect(Collectors.toList());
        }

        return ResponseData.of(HttpStatus.OK, "파일 조회 완료", fileList);
    }


}
