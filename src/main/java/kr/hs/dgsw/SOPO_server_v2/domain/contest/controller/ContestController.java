package kr.hs.dgsw.SOPO_server_v2.domain.contest.controller;

import kr.hs.dgsw.SOPO_server_v2.domain.contest.dto.ContestLoadRes;
import kr.hs.dgsw.SOPO_server_v2.domain.contest.dto.ContestUpdateReq;
import kr.hs.dgsw.SOPO_server_v2.domain.contest.service.ContestService;
import kr.hs.dgsw.SOPO_server_v2.global.page.PageRequest;
import kr.hs.dgsw.SOPO_server_v2.global.response.Response;
import kr.hs.dgsw.SOPO_server_v2.global.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/contest")
@RequiredArgsConstructor
public class ContestController {
    private final ContestService contestService;

    @GetMapping()
    public ResponseData<List<ContestLoadRes>> getContests(PageRequest pageRequest) {
        return contestService.getContests(pageRequest);
    }

    @PostMapping
    public ResponseData<Long> createContest() {
        return contestService.createContest();
    }

    @GetMapping("/{contestId}")
    public ResponseData<ContestLoadRes> getContest(
            @PathVariable Long contestId) {
        return contestService.findOneContest(contestId);
    }

    @PatchMapping("/{contestId}")
    public Response updateContest(@PathVariable Long contestId, @RequestBody ContestUpdateReq updateReq) {
        return contestService.updateContest(contestId, updateReq);
    }

    @DeleteMapping
    public Response deleteContest(@RequestParam Long contestId) {
        return contestService.deleteContest(contestId);
    }

    @PatchMapping("/state")
    public Response changeContestState(@RequestParam Long contestId) {
        return contestService.changeContestState(contestId);
    }
}