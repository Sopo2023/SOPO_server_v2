package kr.hs.dgsw.SOPO_server_v2.domain.member.presentation.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import kr.hs.dgsw.SOPO_server_v2.domain.member.presentation.dto.req.MemberModifyReq;
import kr.hs.dgsw.SOPO_server_v2.domain.member.service.MemberProfileService;
import kr.hs.dgsw.SOPO_server_v2.domain.member.service.MemberService;
import kr.hs.dgsw.SOPO_server_v2.global.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Member", description = "Member Api")
@RestController
@RequestMapping(value = "/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final MemberProfileService memberProfileService;
    @PatchMapping("/profile")
    public Response modifyMember(
            @RequestBody MemberModifyReq memberModifyReq){
        return memberProfileService.memberModify(memberModifyReq);
    }

    @PatchMapping()
    public Response deleteMember(
            @RequestParam String memberId
    ){
        return memberService.deleteMember(memberId);
    }
}
