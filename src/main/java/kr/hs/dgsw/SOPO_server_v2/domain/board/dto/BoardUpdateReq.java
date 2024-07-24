package kr.hs.dgsw.SOPO_server_v2.domain.board.dto;

public record BoardUpdateReq(
        String boardTitle,
        String boardContent
) {
}
