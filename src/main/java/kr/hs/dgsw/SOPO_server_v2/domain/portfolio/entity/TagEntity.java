package kr.hs.dgsw.SOPO_server_v2.domain.portfolio.entity;

import jakarta.persistence.*;
import kr.hs.dgsw.SOPO_server_v2.domain.member.entity.MemberEntity;
import kr.hs.dgsw.SOPO_server_v2.global.common.entity.BaseTimeEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "tb_tag")
@DynamicUpdate
@AllArgsConstructor
public class TagEntity extends BaseTimeEntity {
    @Id
    @Column(name = "tag_id")
    private Long tagId;

    private String tagName;

    @ManyToOne
    @JoinColumn(name = "portfolio_id")
    private PortfolioEntity portfolioEntity;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;
}
