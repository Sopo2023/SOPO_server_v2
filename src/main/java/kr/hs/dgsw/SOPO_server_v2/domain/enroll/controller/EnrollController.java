package kr.hs.dgsw.SOPO_server_v2.domain.enroll.controller;

import kr.hs.dgsw.SOPO_server_v2.domain.enroll.service.EnrollService;
import kr.hs.dgsw.SOPO_server_v2.global.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enroll")
@RequiredArgsConstructor
public class EnrollController {
    private final EnrollService enrollService;

    @PatchMapping
    public Response patch(@RequestParam Long contestId){
        return enrollService.toggle(contestId);
    }

}
