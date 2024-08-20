package kr.hs.dgsw.SOPO_server_v2.domain.comment.controller;

import kr.hs.dgsw.SOPO_server_v2.domain.comment.dto.CommentCommandReq;
import kr.hs.dgsw.SOPO_server_v2.domain.comment.dto.CommentLoadRes;
import kr.hs.dgsw.SOPO_server_v2.domain.comment.dto.CommentUpdateReq;
import kr.hs.dgsw.SOPO_server_v2.domain.comment.service.CommentService;
import kr.hs.dgsw.SOPO_server_v2.global.response.Response;
import kr.hs.dgsw.SOPO_server_v2.global.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    // 댓글 조회
    @GetMapping("{boardId}")
    public ResponseData<List<CommentLoadRes>> getComments(@PathVariable Long boardId) {
        return commentService.getComments(boardId);
    }

    // 댓글 & 대댓글 생성
    @PostMapping("{boardId}")
    public Response createComment(@PathVariable Long boardId, @RequestBody CommentCommandReq commandReq) {
        return commentService.createComment(boardId, commandReq);
    }

    // 댓글 수정
    @PatchMapping("{commentId}")
    public Response updateComment(@PathVariable Long commentId, @RequestBody CommentUpdateReq updateReq) {
        return commentService.updateComment(commentId, updateReq);
    }


    // 댓글 삭제
    @DeleteMapping
    public Response deleteComment(@RequestParam Long commentId) {
        return commentService.deleteComment(commentId);
    }


}
