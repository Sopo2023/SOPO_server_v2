package kr.hs.dgsw.SOPO_server_v2.domain.portfolio.presentation.dto.res;

import kr.hs.dgsw.SOPO_server_v2.domain.portfolio.entity.PortfolioEntity;

public record LoadPortfolioRes(
        String portfolioTitle,
        String memberName
) {
    public static LoadPortfolioRes of(PortfolioEntity portfolioEntity) {
        return new LoadPortfolioRes(
                portfolioEntity.getPortfolioTitle(),
                portfolioEntity.getMember().getMemberName()
        );
    }
}
