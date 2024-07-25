package kr.hs.dgsw.SOPO_server_v2.global.infra.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.hs.dgsw.SOPO_server_v2.global.error.ErrorResponse;
import kr.hs.dgsw.SOPO_server_v2.global.error.exception.StatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtExceptionFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException e) {
            setErrorResponse(response, StatusEnum.EXPIRED_TOKEN);
        } catch (MalformedJwtException e) {
            setErrorResponse(response, StatusEnum.MALFORMED_JWT);
        } catch (UnsupportedJwtException e) {
            setErrorResponse(response, StatusEnum.UNSUPPORTED_JWT);
        } catch (IllegalArgumentException e) {
            setErrorResponse(response, StatusEnum.ILLEGAL_ARGUMENT);
        }
    }

    private void setErrorResponse(HttpServletResponse response, StatusEnum error) {
        try {
            responseToClient(response, ErrorResponse.of(error, error.getMessage()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void responseToClient(HttpServletResponse response, ErrorResponse errorResponse) throws IOException {
        response.setStatus(errorResponse.getStatus());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }

}
