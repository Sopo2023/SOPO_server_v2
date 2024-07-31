package kr.hs.dgsw.SOPO_server_v2.global.error.custom.member;

import kr.hs.dgsw.SOPO_server_v2.global.error.exception.BusinessException;
import kr.hs.dgsw.SOPO_server_v2.global.error.exception.StatusEnum;

public class IdAlreadyExistException extends BusinessException {
    public static final BusinessException EXCEPTION = new IdAlreadyExistException();

    public IdAlreadyExistException() {
        super(StatusEnum.ID_ALREADY_EXIST);
    }
}
