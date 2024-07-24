package kr.hs.dgsw.SOPO_server_v2.domain.board.dto;

import kr.hs.dgsw.SOPO_server_v2.domain.board.entity.BoardEntity;

public record BoardLoadRes (
        Long boardId,
        String boardTitle,
        String boardContent,
        Integer boardLikeCount
        // List<String> fileUrls,
        // Long memberId
){
    public static BoardLoadRes of(BoardEntity board) {
        return new BoardLoadRes(
                board.getBoardId(),
                board.getBoardTitle(),
                board.getBoardTitle(),
                board.getBoardLikeCount()
                //board.getFile() Url을 String 으로 묶어서 받아야 함.
        );
    }
}
