package kr.hs.dgsw.SOPO_server_v2.domain.enroll.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import kr.hs.dgsw.SOPO_server_v2.domain.contest.entity.ContestEntity;
import kr.hs.dgsw.SOPO_server_v2.domain.member.entity.MemberEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@Table(name = "tbl_enroll")
public class EnrollEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long EnrollIdx;

    @ManyToOne()
    @JoinColumn(name = "fk_member_id")
    private MemberEntity member;

    @ManyToOne()
    @JoinColumn(name = "fk_contest_id")
    private ContestEntity contest;

}
