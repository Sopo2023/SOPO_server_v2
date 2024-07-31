package kr.hs.dgsw.SOPO_server_v2.domain.contest.controller;

import kr.hs.dgsw.SOPO_server_v2.domain.contest.dto.ContestLoadRes;
import kr.hs.dgsw.SOPO_server_v2.domain.contest.dto.ContestUpdateReq;
import kr.hs.dgsw.SOPO_server_v2.domain.contest.service.ContestService;
import kr.hs.dgsw.SOPO_server_v2.global.response.Response;
import kr.hs.dgsw.SOPO_server_v2.global.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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

    @GetMapping("/all")
    public ResponseData<List<ContestLoadRes>> getContests() {
        return contestService.getContests();
    }

    @PostMapping
    public ResponseData<Long> createContest() {
        return contestService.createContest();
    }

    @GetMapping
    public ResponseData<ContestLoadRes> getContest(@RequestParam Long contestId) {
        return contestService.findOneContest(contestId);
    }

    @PatchMapping
    public Response updateContest(@RequestParam Long contestId, @RequestBody ContestUpdateReq updateReq) {
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
