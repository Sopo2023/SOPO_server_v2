package kr.hs.dgsw.SOPO_server_v2.domain.auth.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kr.hs.dgsw.SOPO_server_v2.domain.auth.dto.req.ReProvideTokenReq;
import kr.hs.dgsw.SOPO_server_v2.domain.auth.dto.res.ReProvideTokenRes;
import kr.hs.dgsw.SOPO_server_v2.domain.auth.service.AuthTokenService;
import kr.hs.dgsw.SOPO_server_v2.global.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Auth", description = "Auth Api")
@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthTokenService authTokenService;

    @PostMapping("/reProvide")
    public ResponseData<ReProvideTokenRes> reProvideToken(
            @RequestBody @Valid ReProvideTokenReq reProvideTokenReq
            ){
        return authTokenService.reProvideToken(reProvideTokenReq);
    }
}
