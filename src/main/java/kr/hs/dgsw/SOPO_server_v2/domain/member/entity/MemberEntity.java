package kr.hs.dgsw.SOPO_server_v2.domain.member.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import kr.hs.dgsw.SOPO_server_v2.domain.file.entity.FileEntity;
import kr.hs.dgsw.SOPO_server_v2.domain.member.enums.MemberCategory;
import kr.hs.dgsw.SOPO_server_v2.domain.member.enums.MemberState;
import kr.hs.dgsw.SOPO_server_v2.global.common.entity.BaseTimeEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "tb_member")
@DynamicUpdate
public class MemberEntity extends BaseTimeEntity {
    @Id
    @Size(min = 0, max = 15)
    @Column(name = "member_id")
    private String memberId;

    @Column(name = "member_name")
    private String memberName;

    @Size(min = 0, max = 300)
    @Column(name = "member_email")
    private String memberEmail;

    @Column(name = "member_password")
    private String memberPassword;

    @Column(name = "member_school")
    private String memberSchool;

    @Column(name = "member_category")
    @Enumerated(EnumType.STRING)
    private MemberCategory memberCategory;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private MemberState memberState = MemberState.ACTIVE;

    @Column(name = "member_fcm_token", columnDefinition = "TEXT")
    private String memberFcmToken;

    @Builder.Default
    private Boolean isOffAlarm = Boolean.FALSE;

    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "member_file")
    private FileEntity memberProfile;
}
