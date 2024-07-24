package kr.hs.dgsw.SOPO_server_v2.domain.file.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import kr.hs.dgsw.SOPO_server_v2.domain.board.entity.BoardEntity;
import kr.hs.dgsw.SOPO_server_v2.domain.contest.entity.ContestEntity;
import kr.hs.dgsw.SOPO_server_v2.domain.member.entity.MemberEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "tbl_file")
@NoArgsConstructor
public class FileEntity {

    // 파일 아이디
    @Id
    @Column(name = "file_id")
    private Long fileId;

    // 파일 이름
    @Column(name = "file_name")
    private String fileName;

    // 파일 url
    @Column(name = "file_url")
    private String fileUrl;

    // 게시물 아이디
    @ManyToOne
    @JoinColumn(name = "board_id")
    private BoardEntity board;

    // 대회 아이디
    @ManyToOne
    @JoinColumn(name = "contest_id")
    private ContestEntity contest;

    // 유저 아이디
    @ManyToOne
    @JoinColumn(name = "member_id")
    private MemberEntity member;

}
