package kr.hs.dgsw.SOPO_server_v2.domain.board.controller;

import kr.hs.dgsw.SOPO_server_v2.domain.board.dto.BoardLoadRes;
import kr.hs.dgsw.SOPO_server_v2.domain.board.dto.BoardUpdateReq;
import kr.hs.dgsw.SOPO_server_v2.domain.board.service.BoardService;
import kr.hs.dgsw.SOPO_server_v2.global.response.Response;
import kr.hs.dgsw.SOPO_server_v2.global.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/all")
    public ResponseData<List<BoardLoadRes>> getBoards() {
        return boardService.getBoards();
    }

    @PostMapping
    public ResponseData<Long> createBoard() {
        return boardService.createBoard();
    }

    @GetMapping
    public ResponseData<BoardLoadRes> getBoard(Long boardId) {
        return boardService.findOneBoard(boardId);
    }

    @PatchMapping
    public Response loadBoard(Long boardId, BoardUpdateReq updateReq) {
        return boardService.loadBoard(boardId, updateReq);
    }

    @DeleteMapping
    public Response deleteBoard(Long boardId) {
        return boardService.deleteBoard(boardId);
    }

}
