package kr.hs.dgsw.SOPO_server_v2.global.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static org.springframework.http.HttpStatus.FORBIDDEN;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum StatusEnum {
    //smtp
    NOT_DUPLICATED_AUTH_CODE(400, "Not Duplicated AuthCode"),
    EXPIRED_TOKEN(401, "Expired token"),
    INVALID_TOKEN(401, "Invalid token"),
    REFRESH_TOKEN_NOT_FOUND(401, "RefreshToken not found"),
    MALFORMED_JWT(400, "Jwt is malformed"),
    UNSUPPORTED_JWT(400, "Jwt is unsupported"),
    ILLEGAL_ARGUMENT(400, "IllegalArgumentException occurred"),
    WRONG_TOKEN_TYPE(400, "Wrong token type"),

    // general
    OK(200, "OK"),
    CREATED(201, "Created"),
    BAD_REQUEST(400, "Bad request"),
    INTERNAL_SERVER_ERROR(500, "Internal server error"),

    //user
    MEMBER_NOT_EXISTS(403, "Member Not exists"),
    MEMBER_EXISTS(403, "Member exists"),
    USER_NOT_COINCIDE(401, "User not coincide"),
    USER_NOT_FOUND(404, "User not found"),
    NOT_AUTHENTICATED(401, "NotAuthenticated"),
    PERMISSION_DENIED(403, "Permission denied"),
    WITHDRAWAL_MEMBER(400, "Withdrawal member"),
    INVALID_ROLE(403, "유효하지 않은 권한"),
    WRONG_PASSWORD(403, "비밀번호가 옳지 않습니다"),

    //file
    FILE_NOT_FOUND(404, "File not found"),
    FILE_EXISTS(403, "File exists"),

    //email
    UNABLE_TO_SEND_EMAIL(403, "Unable to send email"),
    EMAIL_ALREADY_EXIST(404, "이메일이 이미 존재합니다"),
    CODE_IS_WRONG(404, "인증코드가 옳지 않습니다."),

    //fcm
    MESSAGE_SEND_FAILED(403, "Message send failed"),
    TOKEN_NOT_PROVIDED(400, "Token not provided"),

    CLOUD_EXCEPTION(500, "Cloud exception"),

    // board
    BOARD_NOT_FOUND(404, "Board not found"),

    // contest
    CONTEST_NOT_FOUND(404, "Contest not found");

    private final int statusCode;
    private final String message;
}