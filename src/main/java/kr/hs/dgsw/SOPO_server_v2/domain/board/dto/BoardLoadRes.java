package kr.hs.dgsw.SOPO_server_v2.domain.board.dto;

import kr.hs.dgsw.SOPO_server_v2.domain.board.entity.BoardEntity;
import kr.hs.dgsw.SOPO_server_v2.domain.file.dto.FileRes;
import kr.hs.dgsw.SOPO_server_v2.domain.file.entity.FileEntity;

import java.util.List;


public record BoardLoadRes (
        Long boardId,
        String boardTitle,
        String boardContent,
        Integer boardLikeCount,
        String memberName,
        List<FileRes> files
){
    public static BoardLoadRes of(BoardEntity board, List<FileEntity> fileEntities) {
        return new BoardLoadRes(
                board.getBoardId(),
                board.getBoardTitle(),
                board.getBoardTitle(),
                board.getBoardLikeCount(),
                board.getMember().getMemberName(),
                fileEntities.stream().map(FileRes::of).toList()
        );
    }
}