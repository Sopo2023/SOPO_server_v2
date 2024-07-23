package kr.hs.dgsw.SOPO_server_v2.global.response;

import org.springframework.http.HttpStatus;

public record ResponseData<T>(
        int status,
        String message,
        T data
) {
    public static <T> ResponseData<T> of(HttpStatus status, String message, T data) {
        return new ResponseData<>(status.value(), message, data);
    }
}
