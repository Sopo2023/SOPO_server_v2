package kr.hs.dgsw.SOPO_server_v2.domain.contest.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import kr.hs.dgsw.SOPO_server_v2.domain.contest.enums.ContestState;
import kr.hs.dgsw.SOPO_server_v2.domain.file.entity.FileEntity;
import kr.hs.dgsw.SOPO_server_v2.domain.member.entity.MemberEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Getter
@Entity
@Table(name = "tbl_contest")
@NoArgsConstructor
public class ContestEntity {

    // 대회 아이디
    @Id
    @Column(name = "contest_id")
    private Long contestId;

    // 대회 제목
    @Column(name = "contest_title")
    private String contestTitle;

    // 대회 내용
    @Column(name = "contest_content")
    private String contestContent;

    // 대회 상태
    @Column(name = "contest_state")
    private ContestState contestState;

    // 대회 정원
    @Column(name = "contest_max")
    private Integer contestMax;

    // 대회 인원
    @Column(name = "contest_person")
    private Integer contestPerson;

    // 대회 마감일
    @Column(name = "contest_date_time")
    private Date contestDateTime;

    // 대회 좋아요
    @Column(name = "contest_like_count")
    private Integer contestLikeCount;

    // 유저 아이디
    @ManyToOne
    @JoinColumn(name = "member_id")
    private MemberEntity member;

    // 대회 파일
    @OneToMany(mappedBy = "contest", cascade = CascadeType.ALL, orphanRemoval = true) // 읽기만, 게시물 삭제될 때 함께 삭제
    private List<FileEntity> file;

}
