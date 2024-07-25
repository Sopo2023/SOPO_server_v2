package kr.hs.dgsw.SOPO_server_v2.domain.auth.service;

import kr.hs.dgsw.SOPO_server_v2.domain.member.repository.MemberRepository;
import kr.hs.dgsw.SOPO_server_v2.global.error.custom.email.EmailAlreadyExistsException;
import kr.hs.dgsw.SOPO_server_v2.global.infra.mail.MailService;
import kr.hs.dgsw.SOPO_server_v2.global.infra.mail.RandomCode;
import kr.hs.dgsw.SOPO_server_v2.global.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.thymeleaf.TemplateEngine;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

@Component
@RequiredArgsConstructor
public class AuthEmailService {
    private static final String AUTH_CODE_PREFIX = "AuthCode_";
    private final MemberRepository memberRepository;
    private final MailService mailService;
    private final StringRedisTemplate redisTemplate;
    private final TemplateEngine templateEngine;

    @Value("${spring.mail.auth-code-expiration-millis}")
    private long authCodeExpirationMillis;

    public boolean checkEmailDuplicate(String email) {
        if (memberRepository.existsByMemberEmail(email))
            throw EmailAlreadyExistsException.EXCEPTION;
        return true;
    }

    public Response sendMail(String email) {
        try {
            String decodedEmail = URLDecoder.decode(email, StandardCharsets.UTF_8);
            checkEmailDuplicate(decodedEmail);
            String authCode = RandomCode.generate();
            mailService.verificationCode(
                    decodedEmail,
                    "SOPO 회원가입을 위한 이메일 인증코드",
                    authCode
            );
            redisTemplate.opsForValue()
                    .set(AUTH_CODE_PREFIX + decodedEmail, authCode, Duration.ofMillis(authCodeExpirationMillis));

            return Response.of(HttpStatus.OK, "이메일 전송 성공");
        } catch (Exception e) {
            return Response.of(HttpStatus.INTERNAL_SERVER_ERROR, "이메일 전송 실패: " + e.getMessage());
        }
    }

    public boolean verifiedCode(String email, String code){
        checkEmailDuplicate(email);
        String email1 = URLDecoder.decode(email, StandardCharsets.UTF_8);
        String authCode = redisTemplate.opsForValue().get(AUTH_CODE_PREFIX + email);
        return StringUtils.hasText(authCode) && authCode.equals(code);
    }
}
