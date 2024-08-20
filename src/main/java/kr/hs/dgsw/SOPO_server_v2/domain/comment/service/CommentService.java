package kr.hs.dgsw.SOPO_server_v2.domain.comment.service;

import jakarta.transaction.Transactional;
import kr.hs.dgsw.SOPO_server_v2.domain.board.entity.BoardEntity;
import kr.hs.dgsw.SOPO_server_v2.domain.board.repository.BoardRepository;
import kr.hs.dgsw.SOPO_server_v2.domain.comment.dto.CommentCommandReq;
import kr.hs.dgsw.SOPO_server_v2.domain.comment.dto.CommentLoadRes;
import kr.hs.dgsw.SOPO_server_v2.domain.comment.dto.CommentUpdateReq;
import kr.hs.dgsw.SOPO_server_v2.domain.comment.entity.CommentEntity;
import kr.hs.dgsw.SOPO_server_v2.domain.comment.repository.CommentRepository;
import kr.hs.dgsw.SOPO_server_v2.domain.member.entity.MemberEntity;
import kr.hs.dgsw.SOPO_server_v2.domain.member.enums.MemberCategory;
import kr.hs.dgsw.SOPO_server_v2.global.error.custom.board.BoardNotFound;
import kr.hs.dgsw.SOPO_server_v2.global.error.custom.comment.CommentNotFound;
import kr.hs.dgsw.SOPO_server_v2.global.error.custom.member.MemberNotCoincideException;
import kr.hs.dgsw.SOPO_server_v2.global.infra.security.GetCurrentMember;
import kr.hs.dgsw.SOPO_server_v2.global.response.Response;
import kr.hs.dgsw.SOPO_server_v2.global.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final GetCurrentMember getCurrentMember;

    // 댓글 & 대댓글 조회
    public ResponseData<List<CommentLoadRes>> getComments(Long boardId) {
        BoardEntity targetBoard = boardRepository.findById(boardId)
                .orElseThrow(() -> BoardNotFound.EXCEPTION);

        // 댓글만 조회하는 코드
        // List<CommentEntity> commentLists = commentRepository.findAllByBoardAndParentId(targetBoard,0L);

        List<CommentEntity> commentLists = commentRepository.findAllByBoard(targetBoard);


        return ResponseData.of(HttpStatus.OK, "댓글 조회 성공", commentLists.stream().map(CommentLoadRes::of).toList());
    }


    // 댓글 & 대댓글 생성
    public Response createComment(Long boardId, CommentCommandReq commentCommandReq) {
        BoardEntity targetBoard = boardRepository.findById(boardId)
                .orElseThrow(() -> BoardNotFound.EXCEPTION);

        MemberEntity writer = getCurrentMember.current();

        commentRepository.save(commentCommandReq.toEntity(targetBoard, writer));

        targetBoard.updateCommentCnt(1);

        return Response.of(HttpStatus.OK, "댓글 생성 성공!");
    }

    // 댓글 수정
    public Response updateComment(Long commentId, CommentUpdateReq commentUpdateReq) {
        MemberEntity writer = getCurrentMember.current();

        CommentEntity comment = commentRepository.findById(commentId)
                .orElseThrow(() -> CommentNotFound.EXCEPTION);

        checkIsWriter(writer, comment);

        comment.update(commentUpdateReq.commentContent());

        return Response.of(HttpStatus.OK, "댓글 수정 완료!");
    }

    // 댓글 삭제
    public Response deleteComment(Long commentId) {
        MemberEntity writer = getCurrentMember.current();

        CommentEntity comment = commentRepository.findById(commentId)
                .orElseThrow(() -> CommentNotFound.EXCEPTION);

        checkIsWriter(writer, comment);

        commentRepository.delete(comment);
        comment.getBoard().updateCommentCnt(-1);
        return Response.of(HttpStatus.OK, "댓글 삭제 완료!");
    }

    private void checkIsWriter(MemberEntity curMember, CommentEntity comment) {
        if (!comment.getMember().getMemberId().equals(curMember.getMemberId()) && curMember.getMemberCategory() == MemberCategory.USER) {
            throw MemberNotCoincideException.EXCEPTION;
        }
    }
}
