package kr.hs.dgsw.SOPO_server_v2.global.error;

import kr.hs.dgsw.SOPO_server_v2.global.error.exception.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private String message;
    private Integer status;
    private LocalDateTime timestamp;
    private String description;

    public static ErrorResponse of(StatusEnum errorCode, String description) {
        return ErrorResponse.builder()
                .message(errorCode.getMessage())
                .status(errorCode.getStatusCode())
                .timestamp(LocalDateTime.now())
                .description(description)
                .build();
    }

    public static ErrorResponse of(int statusCode, String description) {
        return ErrorResponse.builder()
                .message(description)
                .status(statusCode)
                .timestamp(LocalDateTime.now())
                .description(description)
                .build();
    }
}
