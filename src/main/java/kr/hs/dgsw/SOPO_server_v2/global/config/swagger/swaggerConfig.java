package kr.hs.dgsw.SOPO_server_v2.global.config.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class swaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        SecurityScheme securityScheme = new SecurityScheme()
                .name("Authorization")
                .type(SecurityScheme.Type.HTTP)
                .in(SecurityScheme.In.HEADER)
                .bearerFormat("JWT")
                .scheme("bearer");
        OpenAPI openAPI = new OpenAPI().addSecurityItem(new SecurityRequirement().addList("JWT Token"))
                .components(new Components())
                .info(apiInfo());
        openAPI.getComponents().addSecuritySchemes("JWT Token", securityScheme);
        return openAPI;
    }

    private Info apiInfo() {
        return new Info()
                .title("SOPO_v2")
                .description("Java API")
                .version("0.0.1");
    }
}
