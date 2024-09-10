package kr.hs.dgsw.SOPO_server_v2.domain.portfolio.service;

import kr.hs.dgsw.SOPO_server_v2.domain.portfolio.entity.PortfolioEntity;
import kr.hs.dgsw.SOPO_server_v2.domain.portfolio.enums.PortfolioState;
import kr.hs.dgsw.SOPO_server_v2.domain.portfolio.presentation.dto.req.ModifyPortfolioReq;
import kr.hs.dgsw.SOPO_server_v2.domain.portfolio.presentation.dto.res.LoadPortfolioRes;
import kr.hs.dgsw.SOPO_server_v2.domain.portfolio.repository.PortfolioRepository;
import kr.hs.dgsw.SOPO_server_v2.global.infra.security.GetCurrentMember;
import kr.hs.dgsw.SOPO_server_v2.global.page.PageRequest;
import kr.hs.dgsw.SOPO_server_v2.global.response.Response;
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
    private final GetCurrentMember getCurrentMember;

    public ResponseData<Long> generatePortfolio(){
        PortfolioEntity portfolioEntity = portfolioRepository.save(
                PortfolioEntity.builder()
                        .portfolioContent(null)
                        .portfolioTitle(null)
                        .portfolioState(PortfolioState.PENDING)
                        .tagEntity(null)
                        .member(getCurrentMember.current())
                        .build());

        return ResponseData.of(HttpStatus.OK, "포트폴리오 생성 성공", portfolioEntity.getPortfolioId());
    }

    public Response modifyPortfolio(ModifyPortfolioReq generatePortfolioReq){
        return null;

    }

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
