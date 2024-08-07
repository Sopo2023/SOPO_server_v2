package kr.hs.dgsw.SOPO_server_v2.domain.file.controller;

import kr.hs.dgsw.SOPO_server_v2.domain.file.dto.FileRes;
import kr.hs.dgsw.SOPO_server_v2.domain.file.enums.FileCategory;
import kr.hs.dgsw.SOPO_server_v2.domain.file.service.FileService;
import kr.hs.dgsw.SOPO_server_v2.global.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    @PostMapping
    public ResponseData<List<FileRes>> fileUpload(@RequestParam Long id, @RequestParam FileCategory fileCategory, @RequestPart List<MultipartFile> fileList) {
        return fileService.fileUpload(id, fileCategory, fileList);
    }

    @GetMapping("{id}")
    public ResponseData<List<FileRes>> getFiles(@PathVariable Long id, @RequestParam FileCategory fileCategory) {
        return fileService.getFiles(id, fileCategory);
    }
}
