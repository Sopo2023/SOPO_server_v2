package kr.hs.dgsw.SOPO_server_v2.domain.auth.presentation.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kr.hs.dgsw.SOPO_server_v2.domain.auth.presentation.dto.req.SignInReq;
import kr.hs.dgsw.SOPO_server_v2.domain.auth.presentation.dto.req.SignUpReq;
import kr.hs.dgsw.SOPO_server_v2.domain.auth.presentation.dto.res.TokenRes;
import kr.hs.dgsw.SOPO_server_v2.domain.auth.service.AuthService;
import kr.hs.dgsw.SOPO_server_v2.global.response.Response;
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
