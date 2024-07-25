package kr.hs.dgsw.SOPO_server_v2.global.error.custom.auth;

import kr.hs.dgsw.SOPO_server_v2.global.error.exception.BusinessException;
import kr.hs.dgsw.SOPO_server_v2.global.error.exception.StatusEnum;

public class WrongTokenTypeException extends BusinessException {
    static final BusinessException EXCEPTION = new WrongTokenTypeException();

    private WrongTokenTypeException() {
        super(StatusEnum.WRONG_TOKEN_TYPE);
    }
}
