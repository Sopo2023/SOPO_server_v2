package kr.hs.dgsw.SOPO_server_v2.domain.member.presentation.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import kr.hs.dgsw.SOPO_server_v2.domain.member.presentation.dto.req.MemberModifyReq;
import kr.hs.dgsw.SOPO_server_v2.domain.member.service.MemberService;
import kr.hs.dgsw.SOPO_server_v2.global.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Member", description = "Member Api")
@RestController
@RequestMapping(value = "/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    @PatchMapping
    public Response modifyMember(
            @RequestBody MemberModifyReq memberModifyReq){
        return memberService.memberModify(memberModifyReq);
    }
}
