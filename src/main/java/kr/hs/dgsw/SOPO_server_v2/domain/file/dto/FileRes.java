package kr.hs.dgsw.SOPO_server_v2.domain.file.dto;

import kr.hs.dgsw.SOPO_server_v2.domain.file.entity.FileEntity;
import lombok.Builder;

public record FileRes (
    String fileName,
    String fileUrl
) {

    public static FileRes of(FileEntity file) {
        return new FileRes(
                file.getFileName(),
                file.getFileUrl()
        );
    }

}
