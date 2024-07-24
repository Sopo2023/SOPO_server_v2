package kr.hs.dgsw.SOPO_server_v2.global.error.custom.auth;

import com.b1nd.alimoserver.global.error.exception.BusinessException;
import com.b1nd.alimoserver.global.error.exception.StatusEnum;

public class EncryptException extends BusinessException {
    public static final BusinessException EXCEPTION = new EncryptException();

    public EncryptException() {
        super(StatusEnum.INTERNAL_SERVER_ERROR);
    }
}
