package kr.hs.dgsw.SOPO_server_v2.global.error.custom.member;

import kr.hs.dgsw.SOPO_server_v2.global.error.exception.BusinessException;
import kr.hs.dgsw.SOPO_server_v2.global.error.exception.StatusEnum;

public class UserNotCoincideException extends BusinessException {
    public static final BusinessException EXCEPTION = new UserNotCoincideException();

    public UserNotCoincideException() {
        super(StatusEnum.USER_NOT_COINCIDE);
    }
}
