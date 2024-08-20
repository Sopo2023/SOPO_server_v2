package kr.hs.dgsw.SOPO_server_v2.domain.like.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import kr.hs.dgsw.SOPO_server_v2.domain.board.entity.BoardEntity;
import kr.hs.dgsw.SOPO_server_v2.domain.contest.entity.ContestEntity;
import kr.hs.dgsw.SOPO_server_v2.domain.like.enums.LikeCategory;
import kr.hs.dgsw.SOPO_server_v2.domain.member.entity.MemberEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity(name = "tbl_like")
@Getter
@NoArgsConstructor
@SuperBuilder
public class LikeEntity {

    // 좋아요 idx
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeIdx;

    // 유저 idx
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_member_id")
    private MemberEntity member; // param으로 좋아요 타입 받기

    // 좋아요 타입
    @Enumerated(EnumType.STRING)
    private LikeCategory likeCategory;

    // 게시물 idx
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_board_id")
    private BoardEntity board;

    // 대회 idx
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_contest_id")
    private ContestEntity contest;

}
