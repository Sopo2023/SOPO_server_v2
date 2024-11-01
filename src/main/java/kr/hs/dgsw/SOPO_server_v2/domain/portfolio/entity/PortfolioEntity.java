package kr.hs.dgsw.SOPO_server_v2.domain.portfolio.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import kr.hs.dgsw.SOPO_server_v2.domain.member.entity.MemberEntity;
import kr.hs.dgsw.SOPO_server_v2.domain.portfolio.enums.PortfolioState;
import kr.hs.dgsw.SOPO_server_v2.global.common.entity.BaseTimeEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "tb_portfolio")
@DynamicUpdate
@AllArgsConstructor
public class PortfolioEntity extends BaseTimeEntity {
    @Id
    @Column(name = "portfolio_id")
    private Long portfolioId;

    @Column(name = "portfolio_title")
    private String portfolioTitle;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private PortfolioState portfolioState = PortfolioState.PENDING;


    @Column(name = "portfolio_content")
    private String portfolioContent;

    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "portfolio_tag")
    private TagEntity tagEntity;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private MemberEntity member;
}
