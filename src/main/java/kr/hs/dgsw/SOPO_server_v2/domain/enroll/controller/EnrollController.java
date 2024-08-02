package kr.hs.dgsw.SOPO_server_v2.domain.enroll.controller;

import kr.hs.dgsw.SOPO_server_v2.domain.enroll.service.EnrollService;
import kr.hs.dgsw.SOPO_server_v2.global.response.Response;
import kr.hs.dgsw.SOPO_server_v2.global.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/enroll")
@RequiredArgsConstructor
public class EnrollController {
    private final EnrollService enrollService;

    @PatchMapping
    public Response patch(@RequestParam Long contestId){
        return enrollService.toggle(contestId);
    }

    @GetMapping
    public ResponseData<List<String>> showEnrollPeople(@RequestParam Long contestId) {
       return enrollService.showEnrollPeople(contestId);
    }

    @DeleteMapping("/allow")
    public Response allowContest(@RequestParam Long contestId, @RequestParam String memberId) {
        return enrollService.allowContest(contestId, memberId);
    }

    @DeleteMapping("/refuse")
    public Response refuseContest(@RequestParam Long contestId, @RequestParam String memberId) {
        return enrollService.refuseContest(contestId, memberId);
    }
}
