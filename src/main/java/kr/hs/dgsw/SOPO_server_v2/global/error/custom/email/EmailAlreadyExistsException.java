package kr.hs.dgsw.SOPO_server_v2.global.error.custom.email;

import kr.hs.dgsw.SOPO_server_v2.global.error.exception.BusinessException;
import kr.hs.dgsw.SOPO_server_v2.global.error.exception.StatusEnum;

public class EmailAlreadyExistsException extends BusinessException {
    public static final BusinessException EXCEPTION = new EmailAlreadyExistsException();

    public EmailAlreadyExistsException() {
        super(StatusEnum.EMAIL_ALREADY_EXIST);
    }
}
