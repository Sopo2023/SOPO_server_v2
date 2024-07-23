package kr.hs.dgsw.SOPO_server_v2.global.response;

import org.springframework.http.HttpStatus;

public record Response(int status, String message) {
    public static Response of(HttpStatus status, String message) {
        return new Response(status.value(), message);
    }
}
