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
<<<<<<< HEAD
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
=======
import org.springframework.web.bind.annotation.RequestMapping;
>>>>>>> e493e579002345c8a1e3507d0ba6d7a8691fc148
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
<<<<<<< HEAD
    public ResponseData<ContestLoadRes> getContest(@RequestParam Long contestId) {
=======
    public ResponseData<ContestLoadRes> getContest(Long contestId) {
>>>>>>> e493e579002345c8a1e3507d0ba6d7a8691fc148
        return contestService.findOneContest(contestId);
    }

    @PatchMapping
<<<<<<< HEAD
    public Response updateContest(@RequestParam Long contestId, @RequestBody ContestUpdateReq updateReq) {
        return contestService.updateContest(contestId, updateReq);
    }

    @DeleteMapping
    public Response deleteContest(@RequestParam Long contestId) {
=======
    public Response loadContest(Long contestId, ContestUpdateReq updateReq) {
        return contestService.loadContest(contestId, updateReq);
    }

    @DeleteMapping
    public Response deleteContest(Long contestId) {
>>>>>>> e493e579002345c8a1e3507d0ba6d7a8691fc148
        return contestService.deleteContest(contestId);
    }

    @PatchMapping("/state")
<<<<<<< HEAD
    public Response changeContestState(@RequestParam Long contestId) {
=======
    public Response changeContestState(Long contestId) {
>>>>>>> e493e579002345c8a1e3507d0ba6d7a8691fc148
        return contestService.changeContestState(contestId);
    }
}
