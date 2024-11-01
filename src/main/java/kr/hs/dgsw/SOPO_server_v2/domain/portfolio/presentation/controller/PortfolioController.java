package kr.hs.dgsw.SOPO_server_v2.domain.portfolio.presentation.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.hs.dgsw.SOPO_server_v2.domain.portfolio.presentation.dto.res.LoadPortfolioRes;
import kr.hs.dgsw.SOPO_server_v2.domain.portfolio.service.PortfolioService;
import kr.hs.dgsw.SOPO_server_v2.global.page.PageRequest;
import kr.hs.dgsw.SOPO_server_v2.global.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Portfolio", description = "Portfolio Api")
@RestController
@RequestMapping(value = "/portfolio")
@RequiredArgsConstructor
public class PortfolioController {
    private final PortfolioService portfolioService;

    public ResponseData<Long> generatePortfolio() {
        return portfolioService.generatePortfolio();
    }



    @Operation(description = "최신 포트폴리오 불러오기")
    @GetMapping
    public ResponseData<List<LoadPortfolioRes>> loadPortfolio(
            @ModelAttribute PageRequest pageRequest
            ){
        return portfolioService.pagingPortfolio(pageRequest);
    }


}
