package kr.hs.dgsw.SOPO_server_v2.domain.contest.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import kr.hs.dgsw.SOPO_server_v2.domain.contest.dto.ContestUpdateReq;
import kr.hs.dgsw.SOPO_server_v2.domain.contest.enums.ContestState;
import kr.hs.dgsw.SOPO_server_v2.domain.file.entity.FileEntity;
import kr.hs.dgsw.SOPO_server_v2.domain.member.entity.MemberEntity;
import kr.hs.dgsw.SOPO_server_v2.global.common.entity.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tbl_contest")
@NoArgsConstructor
@SuperBuilder
public class ContestEntity extends BaseTimeEntity { // board, contest 에 유저 이름이랑 아이디 같이 보내주기

    // 대회 아이디
    @Id
    @Column(name = "contest_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contestId;

    // 대회 제목
    @Column(name = "contest_title")
    private String contestTitle;

    // 대회 내용
    @Column(name = "contest_content")
    private String contestContent;

    // 대회 상태
    @Column(name = "contest_state")
    @Enumerated(EnumType.STRING)
    private ContestState contestState = ContestState.ACTIVE;

    // 대회 정원
    @Column(name = "contest_max")
    private Integer contestMax;

    // 대회 인원
    @Column(name = "contest_person")
    private Integer contestPerson;

    // 대회 마감일
    @Column(name = "contest_date_time")
    private LocalDateTime contestDateTime;

    // 대회 좋아요
    @Column(name = "contest_like_count")
    private Integer contestLikeCount = 0;

    // 유저 아이디
    @ManyToOne
    @JoinColumn(name = "member_id")
    private MemberEntity member;

    // 대회 파일
    @OneToMany(mappedBy = "contest", cascade = CascadeType.ALL, orphanRemoval = true) // 읽기만, 게시물 삭제될 때 함께 삭제
    private List<FileEntity> file;

    // 대회 유저 이름
    @ManyToMany
    @JoinColumn(name = "member_id_list")
    private List<MemberEntity> memberIdList;


    public void update(ContestUpdateReq updateReq) {
        this.contestTitle = updateReq.contestTitle();
        this.contestContent = updateReq.contestContent();
        this.contestMax = updateReq.contestMax();
        this.contestPerson = updateReq.contestPerson();
        this.contestDateTime = updateReq.contestDateTime();
    }

    public void likeUpdate(int contestLikeCount) {
        this.contestLikeCount += contestLikeCount;
    }

    public void addContestPerson(int contestPerson) {
        this.contestPerson += contestPerson;
    }

    public void addAllowMember(MemberEntity member) {
        this.memberIdList.add(member);
    }

    public void stateUpdateActive() {
        this.contestState = ContestState.ACTIVE;
    }

    public void stateUpdateDisabled() {
        this.contestState = ContestState.DISABLED;
    }
}