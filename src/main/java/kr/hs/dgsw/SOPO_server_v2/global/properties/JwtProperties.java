package kr.hs.dgsw.SOPO_server_v2.global.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties("application.jwt")
public class JwtProperties {
    private String secretKey;
    private Long accessExpire;
    private Long refreshExpire;
}
