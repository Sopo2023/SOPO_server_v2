package kr.hs.dgsw.SOPO_server_v2.domain.comment.dto;

import kr.hs.dgsw.SOPO_server_v2.domain.board.entity.BoardEntity;
import kr.hs.dgsw.SOPO_server_v2.domain.comment.entity.CommentEntity;
import kr.hs.dgsw.SOPO_server_v2.domain.member.entity.MemberEntity;

public record CommentCommandReq (
        Long parentId,
        String commentContent
) {
    public CommentEntity toEntity(BoardEntity targetBoard, MemberEntity writer){
        return CommentEntity.builder()
                .commentContent(commentContent)
                .board(targetBoard)
                .member(writer)
                .parentId(parentId)
                .build();
    }
}
