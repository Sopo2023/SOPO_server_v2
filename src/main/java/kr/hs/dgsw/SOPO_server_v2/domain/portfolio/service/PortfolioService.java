package kr.hs.dgsw.SOPO_server_v2.domain.portfolio.service;

import kr.hs.dgsw.SOPO_server_v2.domain.portfolio.entity.PortfolioEntity;
import kr.hs.dgsw.SOPO_server_v2.domain.portfolio.enums.PortfolioState;
import kr.hs.dgsw.SOPO_server_v2.domain.portfolio.presentation.dto.res.LoadPortfolioRes;
import kr.hs.dgsw.SOPO_server_v2.domain.portfolio.repository.PortfolioRepository;
import kr.hs.dgsw.SOPO_server_v2.global.page.PageRequest;
import kr.hs.dgsw.SOPO_server_v2.global.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PortfolioService {
    private final PortfolioRepository portfolioRepository;

    public ResponseData<List<LoadPortfolioRes>> pagingPortfolio(PageRequest pageRequest){
        List<PortfolioEntity> portfolioEntities = portfolioRepository.findPortfoliosByState(PortfolioState.ACTIVE);

        List<LoadPortfolioRes> loadPortfolioReqList = portfolioEntities.stream()
                .map(LoadPortfolioRes::of)
                .skip((pageRequest.page() - 1) * pageRequest.size())
                .limit(pageRequest.size())
                .collect(Collectors.toList());

        return ResponseData.of(HttpStatus.OK, "성공", loadPortfolioReqList);
    }
}
