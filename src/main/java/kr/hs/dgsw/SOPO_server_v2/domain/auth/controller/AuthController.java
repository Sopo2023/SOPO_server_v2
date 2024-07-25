package kr.hs.dgsw.SOPO_server_v2.domain.auth.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kr.hs.dgsw.SOPO_server_v2.domain.auth.dto.req.ReProvideTokenReq;
import kr.hs.dgsw.SOPO_server_v2.domain.auth.dto.req.SignUpReq;
import kr.hs.dgsw.SOPO_server_v2.domain.auth.dto.res.ReProvideTokenRes;
import kr.hs.dgsw.SOPO_server_v2.domain.auth.service.AuthEmailService;
import kr.hs.dgsw.SOPO_server_v2.domain.auth.service.AuthService;
import kr.hs.dgsw.SOPO_server_v2.domain.auth.service.AuthTokenService;
import kr.hs.dgsw.SOPO_server_v2.global.response.Response;
import kr.hs.dgsw.SOPO_server_v2.global.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Auth", description = "Auth Api")
@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthTokenService authTokenService;
    private final AuthEmailService authEmailService;
    private final AuthService authService;

    @PostMapping("/reProvide")
    public ResponseData<ReProvideTokenRes> reProvideToken(
            @RequestBody @Valid ReProvideTokenReq reProvideTokenReq
            ){
        return authTokenService.reProvideToken(reProvideTokenReq);
    }

    @PostMapping("/email")
    public Response requestVerificationCode(
            @RequestParam(value = "email", required = true) @Validated String email
    ) {
        return authEmailService.sendMail(email);
    }

    @PostMapping("/signUp")
    public Response signUp(
            @RequestBody @Valid SignUpReq signUpReq
            ){
        return authService.signUp(signUpReq);
    }
}
