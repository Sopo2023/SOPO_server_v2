package kr.hs.dgsw.SOPO_server_v2.global.config.security;

import kr.hs.dgsw.SOPO_server_v2.global.error.ErrorResponse;
import kr.hs.dgsw.SOPO_server_v2.global.error.exception.StatusEnum;
import kr.hs.dgsw.SOPO_server_v2.global.infra.jwt.JwtExceptionFilter;
import kr.hs.dgsw.SOPO_server_v2.global.infra.jwt.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtFilter jwtFilter;
    private final JwtExceptionFilter jwtExceptionFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .cors()
                .and()
                .csrf().disable()
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterAfter(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtExceptionFilter, JwtFilter.class)
                .authorizeHttpRequests()
                .requestMatchers("/v3/api-docs/**", "/swagger-ui/**").permitAll()
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers("/email/**").permitAll()
                .requestMatchers("/re_provide/**").permitAll()
                .requestMatchers("/file/**").hasAuthority("ROLE_ACTIVE")
                .requestMatchers("/contest/**").authenticated()
                .requestMatchers("/like/**").hasAuthority("ROLE_ACTIVE")
                .anyRequest().authenticated()
                .and()
                .formLogin().disable()
                .exceptionHandling()
                .accessDeniedHandler((req, res, e) -> jwtExceptionFilter.responseToClient(res, ErrorResponse.of(StatusEnum.INVALID_ROLE, "권한이 없습니다")))
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.FORBIDDEN));

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOriginPattern("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}