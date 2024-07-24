package kr.hs.dgsw.SOPO_server_v2.global.error.custom.member;

import com.b1nd.alimoserver.global.error.exception.BusinessException;
import com.b1nd.alimoserver.global.error.exception.StatusEnum;

public class IsNotParentException extends BusinessException {
    public static final BusinessException EXCEPTION = new IsNotParentException();

    public IsNotParentException() {
        super(StatusEnum.IS_NOT_PARENT);
    }
}

