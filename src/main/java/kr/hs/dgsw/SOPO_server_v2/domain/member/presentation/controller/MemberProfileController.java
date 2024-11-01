package kr.hs.dgsw.SOPO_server_v2.domain.member.presentation.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.hs.dgsw.SOPO_server_v2.domain.member.presentation.dto.req.MemberModifyReq;
import kr.hs.dgsw.SOPO_server_v2.domain.member.presentation.dto.res.ReadProfileRes;
import kr.hs.dgsw.SOPO_server_v2.domain.member.service.MemberProfileService;
import kr.hs.dgsw.SOPO_server_v2.global.response.Response;
import kr.hs.dgsw.SOPO_server_v2.global.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Profile", description = "Profile Api")
@RestController
@RequestMapping(value = "/profile")
@RequiredArgsConstructor
public class MemberProfileController {

    private final MemberProfileService memberProfileService;

    @Operation(description = "멤버 프로필 수정")
    @PatchMapping
    public Response modifyMember(
            @RequestBody MemberModifyReq memberModifyReq) {
        return memberProfileService.memberModify(memberModifyReq);
    }

    @Operation(description = "나의 프로필 보기")
    @GetMapping
    public ResponseData<ReadProfileRes> readProfile(){
        return memberProfileService.readProfile();
    }


}

