package kr.hs.dgsw.SOPO_server_v2.domain.comment.dto;

import kr.hs.dgsw.SOPO_server_v2.domain.comment.entity.CommentEntity;

public record CommentLoadRes(Long commentId, Long parentId, String content) {
    public static CommentLoadRes of(CommentEntity comment) {
        return new CommentLoadRes(
                comment.getCommentId(),
                comment.getParentId(),
                comment.getCommentContent()
        );
    }
}
