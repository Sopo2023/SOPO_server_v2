package kr.hs.dgsw.SOPO_server_v2.domain.member.presentation.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.hs.dgsw.SOPO_server_v2.domain.member.presentation.dto.req.MemberModifyReq;
import kr.hs.dgsw.SOPO_server_v2.domain.member.presentation.dto.res.ReadProfileRes;
import kr.hs.dgsw.SOPO_server_v2.domain.member.service.MemberProfileService;
import kr.hs.dgsw.SOPO_server_v2.domain.member.service.MemberService;
import kr.hs.dgsw.SOPO_server_v2.global.response.Response;
import kr.hs.dgsw.SOPO_server_v2.global.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Member", description = "Member Api")
@RestController
@RequestMapping(value = "/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    @Operation(description = "회원탈퇴")
    @PatchMapping()
    public Response deleteMember(){
        return memberService.deleteMember();
    }
    @Operation(description = "나의 프로필 보기")
    @GetMapping
    public ResponseData<ReadProfileRes> readProfile(){
        return memberService.readProfile();
    }
}
