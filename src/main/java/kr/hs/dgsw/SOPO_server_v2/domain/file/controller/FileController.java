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
    public ResponseData<List<FileRes>> fileUpload(@RequestParam Long id, @RequestParam FileCategory file_category, @RequestPart List<MultipartFile> file_list) {
        return fileService.fileUpload(id, file_category, file_list);
    }

    @GetMapping("{id}")
    public ResponseData<List<FileRes>> getFiles(@PathVariable Long id, @RequestParam FileCategory file_category) {
        return fileService.getFiles(id, file_category);
    }
}
