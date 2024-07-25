package kr.hs.dgsw.SOPO_server_v2.domain.auth.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import kr.hs.dgsw.SOPO_server_v2.domain.auth.service.AuthEmailService;
import kr.hs.dgsw.SOPO_server_v2.global.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Email", description = "Email Api")
@RestController
@RequestMapping(value = "/email")
@RequiredArgsConstructor
public class EmailController {
    private final AuthEmailService authEmailService;

    @PostMapping("/email")
    public Response requestVerificationCode(
            @RequestParam(value = "email", required = true) @Validated String email
    ) {
        return authEmailService.sendMail(email);
    }
}
