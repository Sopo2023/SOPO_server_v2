package kr.hs.dgsw.SOPO_server_v2.domain.portfolio.presentation.dto.req;

import java.util.List;

public record ModifyPortfolioReq(
        String title,
        String content,
        List<String> tag
) {
}
