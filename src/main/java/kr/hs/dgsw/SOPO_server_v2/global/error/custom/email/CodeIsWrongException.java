package kr.hs.dgsw.SOPO_server_v2.global.error.custom.email;

import kr.hs.dgsw.SOPO_server_v2.global.error.exception.BusinessException;
import kr.hs.dgsw.SOPO_server_v2.global.error.exception.StatusEnum;

public class CodeIsWrongException extends BusinessException {
    public static final BusinessException EXCEPTION = new CodeIsWrongException();

    public CodeIsWrongException() {
        super(StatusEnum.CODE_IS_WRONG);
    }
}
