package kr.hs.dgsw.SOPO_server_v2.domain.comment.dto;

public record CommentCommandReq (
        Long commentId,
        String commentContent
)
{
}
