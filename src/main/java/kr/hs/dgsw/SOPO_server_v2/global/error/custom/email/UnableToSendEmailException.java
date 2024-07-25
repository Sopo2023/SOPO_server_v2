package kr.hs.dgsw.SOPO_server_v2.global.error.custom.email;

import kr.hs.dgsw.SOPO_server_v2.global.error.exception.BusinessException;
import kr.hs.dgsw.SOPO_server_v2.global.error.exception.StatusEnum;

public class UnableToSendEmailException extends BusinessException {
    public static final BusinessException EXCEPTION = new UnableToSendEmailException();

    public UnableToSendEmailException() {
        super(StatusEnum.UNABLE_TO_SEND_EMAIL);
    }
}
