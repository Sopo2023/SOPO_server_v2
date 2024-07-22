package kr.hs.dgsw.SOPO_server_v2.domain.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class MemberEntity {
    @Id
    @Column(name = "member_id")
    private String memberId;
}
