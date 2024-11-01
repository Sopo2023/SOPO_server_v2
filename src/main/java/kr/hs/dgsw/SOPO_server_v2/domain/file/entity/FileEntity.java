package kr.hs.dgsw.SOPO_server_v2.domain.file.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import kr.hs.dgsw.SOPO_server_v2.domain.board.entity.BoardEntity;
import kr.hs.dgsw.SOPO_server_v2.domain.contest.entity.ContestEntity;
import kr.hs.dgsw.SOPO_server_v2.domain.file.enums.FileCategory;
import kr.hs.dgsw.SOPO_server_v2.domain.member.entity.MemberEntity;
import kr.hs.dgsw.SOPO_server_v2.global.common.entity.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@Table(name = "tbl_file")
@NoArgsConstructor
@SuperBuilder
public class FileEntity extends BaseTimeEntity {

    // 파일 아이디
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Long fileId;

    // 파일 이름
    @Column(name = "file_name")
    private String fileName;

    // 파일 url
    @Column(name = "file_url")
    private String fileUrl;

    // 파일 카테고리
    @Enumerated(EnumType.STRING)
    private FileCategory fileCategory;

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