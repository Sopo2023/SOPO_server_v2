package kr.hs.dgsw.SOPO_server_v2.domain.auth.presentation.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kr.hs.dgsw.SOPO_server_v2.domain.auth.presentation.dto.req.ReProvideTokenReq;
import kr.hs.dgsw.SOPO_server_v2.domain.auth.presentation.dto.res.ReProvideTokenRes;
import kr.hs.dgsw.SOPO_server_v2.domain.auth.service.AuthTokenService;
import kr.hs.dgsw.SOPO_server_v2.global.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Refresh", description = "Refresh Api")
@RestController
@RequestMapping(value = "/re_provide")
@RequiredArgsConstructor
public class TokenController {

    private final AuthTokenService authTokenService;

    @Operation(description = "토큰 refresh")
    @PostMapping("")
    public ResponseData<ReProvideTokenRes> reProvideToken(
            @RequestBody @Valid ReProvideTokenReq reProvideTokenReq
    ){
        return authTokenService.reProvideToken(reProvideTokenReq);
    }

}
