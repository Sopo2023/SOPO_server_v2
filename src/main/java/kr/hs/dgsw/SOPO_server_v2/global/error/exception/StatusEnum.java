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
    NOT_DUPLICATED_AUTH_CODE(400, "인증코드가 일치하지 않습니다."),
    EXPIRED_TOKEN(401, "만료된 토큰입니다."),
    INVALID_TOKEN(401, "유효하지 않은 토큰입니다."),
    REFRESH_TOKEN_NOT_FOUND(401, "리프레시 토큰을 찾을 수 없습니다."),
    MALFORMED_JWT(400, "변형된 토큰입니다."),
    ILLEGAL_ARGUMENT(400, "요청이 잘못되었습니다."),
    UNSUPPORTED_JWT(400, "지원되지 않습니다."),
    WRONG_TOKEN_TYPE(400, "토큰타입이 옳지 않습니다."),

    // general
    OK(200, "OK"),
    CREATED(201, "Created"),
    BAD_REQUEST(400, "Bad request"),
    INTERNAL_SERVER_ERROR(500, "Internal server error"),

    //member
    MEMBER_NOT_EXISTS(403, "멤버가 존재하지 않습니다."),
    MEMBER_NOT_COINCIDE(401, "멤버가 일치하지 않습니다."),
    MEMBER_NOT_FOUND(404, "멤버를 찾을수 없습니다."),
    NOT_AUTHENTICATED(401, "인증되지 않았습니다."),
    PERMISSION_DENIED(403, "권한이 없습니다."),
    WITHDRAWAL_MEMBER(400, "탈퇴된 멤버입니다."),
    INVALID_ROLE(403, "유효하지 않은 권한"),
    WRONG_PASSWORD(403, "비밀번호가 옳지 않습니다"),
    ID_ALREADY_EXIST(404, "아이디가 이미 존재합니다."),

    //file
    FILE_NOT_FOUND(404, "파일을 찾을 수 없습니다."),
    FILE_EXISTS(403, "파일이 이미 존재합니다."),

    //email
    UNABLE_TO_SEND_EMAIL(403, "이메일을 보낼 수 없습니다."),
    EMAIL_ALREADY_EXIST(404, "이메일이 이미 존재합니다"),
    CODE_IS_WRONG(404, "인증코드가 옳지 않습니다."),
    NEED_AUTH_CODE(404, "인증코드가 없습니다."),

    //fcm
    MESSAGE_SEND_FAILED(403, "메세지 전송에 실패했습니다."),
    TOKEN_NOT_PROVIDED(400, "권한이 없습니다."),

    CLOUD_EXCEPTION(500, "클라우드 에러"),

    // board
    BOARD_NOT_FOUND(404, "게시물을 찾을 수 없습니다."),

    // contest
    CONTEST_NOT_FOUND(404, "대회를 찾을 수 없습니다");

    private final int statusCode;
    private final String message;
}