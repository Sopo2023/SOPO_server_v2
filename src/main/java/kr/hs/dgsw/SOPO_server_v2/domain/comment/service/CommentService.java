package kr.hs.dgsw.SOPO_server_v2.domain.comment.service;

import jakarta.transaction.Transactional;
import kr.hs.dgsw.SOPO_server_v2.domain.board.entity.BoardEntity;
import kr.hs.dgsw.SOPO_server_v2.domain.board.repository.BoardRepository;
import kr.hs.dgsw.SOPO_server_v2.domain.comment.dto.CommentCommandReq;
import kr.hs.dgsw.SOPO_server_v2.domain.comment.dto.CommentUpdateReq;
import kr.hs.dgsw.SOPO_server_v2.domain.comment.entity.CommentEntity;
import kr.hs.dgsw.SOPO_server_v2.domain.comment.entity.SubCommentEntity;
import kr.hs.dgsw.SOPO_server_v2.domain.comment.repository.CommentRepository;
import kr.hs.dgsw.SOPO_server_v2.domain.comment.repository.SubCommentRepository;
import kr.hs.dgsw.SOPO_server_v2.domain.member.entity.MemberEntity;
import kr.hs.dgsw.SOPO_server_v2.domain.member.enums.MemberCategory;
import kr.hs.dgsw.SOPO_server_v2.global.error.custom.board.BoardNotFound;
import kr.hs.dgsw.SOPO_server_v2.global.error.custom.comment.CommentNotFound;
import kr.hs.dgsw.SOPO_server_v2.global.error.custom.member.MemberNotCoincideException;
import kr.hs.dgsw.SOPO_server_v2.global.infra.security.GetCurrentMember;
import kr.hs.dgsw.SOPO_server_v2.global.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final GetCurrentMember getCurrentMember;
    private final SubCommentRepository subCommentRepository;


    // 댓글 생성
    public Response createComment(Long boardId, CommentCommandReq commentCommandReq) {
        MemberEntity curMember = getCurrentMember.current();

        if (commentCommandReq.commentId() == null) { // Comment

            BoardEntity board = boardRepository.findById(boardId)
                    .orElseThrow(() -> BoardNotFound.EXCEPTION);

            CommentEntity.builder()
                    .commentContent(commentCommandReq.commentContent())
                    .board(board)
                    .member(curMember)
                    .children(null)
                    .build();

        } else { // SubComment

            CommentEntity comment = commentRepository.findById(commentCommandReq.commentId())
                    .orElseThrow(() -> CommentNotFound.EXCEPTION);

            SubCommentEntity subComment = SubCommentEntity.builder()
                    .subCommentContent(commentCommandReq.commentContent())
                    .parent(comment)
                    .member(curMember)
                    .build();

            comment.addChildrenComment(subComment);

        }

        return Response.of(HttpStatus.OK, "댓글 생성 성공!");
    }

    // 댓글 수정
    public Response updateComment(Long commentId, CommentUpdateReq commentUpdateReq) {
        MemberEntity curMember = getCurrentMember.current();

        CommentEntity comment = commentRepository.findById(commentId)
                .orElseThrow(() -> CommentNotFound.EXCEPTION);

        notMemberComment(curMember, comment);

        comment.update(commentUpdateReq);

        return Response.of(HttpStatus.OK, "댓글 수정 완료!");
    }

    // 대댓글 수정
    public Response updateSubComment(Long subCommentId, CommentUpdateReq commentUpdateReq) {
        MemberEntity curMember = getCurrentMember.current();

        SubCommentEntity subComment = subCommentRepository.findById(subCommentId)
                .orElseThrow(() -> CommentNotFound.EXCEPTION);

        notMemberSubComment(curMember, subComment);

        subComment.update(commentUpdateReq);

        return Response.of(HttpStatus.OK, "대댓글 수정 완료!");
    }

    // 댓글 삭제
//    public Response deleteComment(Long subCommentId) {
//        MemberEntity curMember = getCurrentMember.current();
//
//
//
//    }

    // 대댓글 삭제
//    public Response deleteSubComment(Long subCommentId) {
//        MemberEntity curMember = getCurrentMember.current();
//
//
//    }



    private void notMemberComment(MemberEntity curMember, CommentEntity comment) {
        if (!comment.getMember().getMemberId().equals(curMember.getMemberId()) && curMember.getMemberCategory() == MemberCategory.USER) {
            throw MemberNotCoincideException.EXCEPTION;
        }
    }

    private void notMemberSubComment(MemberEntity curMember, SubCommentEntity comment) {
        if (!comment.getMember().getMemberId().equals(curMember.getMemberId()) && curMember.getMemberCategory() == MemberCategory.USER) {
            throw MemberNotCoincideException.EXCEPTION;
        }
    }



}
