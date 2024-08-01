package kr.hs.dgsw.SOPO_server_v2.domain.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kr.hs.dgsw.SOPO_server_v2.domain.auth.dto.req.ReProvideTokenReq;
import kr.hs.dgsw.SOPO_server_v2.domain.auth.dto.req.SignInReq;
import kr.hs.dgsw.SOPO_server_v2.domain.auth.dto.req.SignUpReq;
import kr.hs.dgsw.SOPO_server_v2.domain.auth.dto.res.ReProvideTokenRes;
import kr.hs.dgsw.SOPO_server_v2.domain.auth.dto.res.TokenRes;
import kr.hs.dgsw.SOPO_server_v2.domain.auth.service.AuthEmailService;
import kr.hs.dgsw.SOPO_server_v2.domain.auth.service.AuthService;
import kr.hs.dgsw.SOPO_server_v2.domain.auth.service.AuthTokenService;
import kr.hs.dgsw.SOPO_server_v2.global.common.dto.res.JsonWebTokenResponse;
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
    private final AuthService authService;

    @Operation(description = "회원가입")
    @PostMapping("/sign_up")
    public Response signUp(
            @RequestBody @Valid SignUpReq signUpReq
            ){
        return authService.signUp(signUpReq);
    }

    @Operation(description = "로그인")
    @PostMapping("/sign_in")
    public ResponseData<TokenRes> signIn(
            @RequestBody @Valid SignInReq signInReq
            ){
        return authService.signIn(signInReq);
    }
}
