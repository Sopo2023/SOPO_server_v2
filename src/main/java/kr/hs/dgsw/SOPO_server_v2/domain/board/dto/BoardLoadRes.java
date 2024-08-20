package kr.hs.dgsw.SOPO_server_v2.domain.board.dto;

import kr.hs.dgsw.SOPO_server_v2.domain.board.entity.BoardEntity;

public record BoardLoadRes (
        Long boardId,
        String boardTitle,
        String boardContent,
        Integer boardLikeCount,
        String memberName,
        Integer boardCommentCount
){
    public static BoardLoadRes of(BoardEntity board) {
        return new BoardLoadRes(
                board.getBoardId(),
                board.getBoardTitle(),
                board.getBoardContent(),
                board.getBoardLikeCount(),
                board.getMember().getMemberName(),
                board.getBoardCommentCount()
        );
    }
}
