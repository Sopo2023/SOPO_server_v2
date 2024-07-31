package kr.hs.dgsw.SOPO_server_v2.domain.board.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import kr.hs.dgsw.SOPO_server_v2.domain.board.dto.BoardUpdateReq;
import kr.hs.dgsw.SOPO_server_v2.domain.file.entity.FileEntity;
import kr.hs.dgsw.SOPO_server_v2.domain.member.entity.MemberEntity;
import kr.hs.dgsw.SOPO_server_v2.global.common.entity.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Entity
@Table(name = "tbl_board")
@NoArgsConstructor
@Setter
@SuperBuilder
public class BoardEntity extends BaseTimeEntity {

    // 게시물 아이디
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long boardId;

    // 게시물 제목
    @Column(name = "board_title")
    private String boardTitle;

    // 게시물 내용
    @Column(name = "board_content")
    private String boardContent;

    // 게시물 좋아요
    @Column(name = "board_like_count")
    private Integer boardLikeCount = 0;

    // 유저 아이디
    @ManyToOne
    @JoinColumn(name = "member_id") // member_id로 참조한다.
    private MemberEntity member;

    // 게시물 파일
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true) // 읽기만, 게시물 삭제될 때 함께 삭제
    private List<FileEntity> file;

    public void update(BoardUpdateReq updateReq) {
        this.boardTitle = updateReq.boardTitle();
        this.boardContent = updateReq.boardContent();
    }

    public void likeUpdate(int boardLikeCount) {
        this.boardLikeCount += boardLikeCount;
    }
}