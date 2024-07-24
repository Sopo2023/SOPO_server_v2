package kr.hs.dgsw.SOPO_server_v2.domain.board.dto;

import kr.hs.dgsw.SOPO_server_v2.domain.board.entity.BoardEntity;

public record BoardCommandRes (
    Long boardId
) {
    public static BoardCommandRes of (BoardEntity board) {
        return new BoardCommandRes(
                board.getBoardId()
        );
    }
}
