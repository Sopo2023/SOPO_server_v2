package kr.hs.dgsw.SOPO_server_v2.global.error.custom.auth;

import kr.hs.dgsw.SOPO_server_v2.global.error.exception.BusinessException;
import kr.hs.dgsw.SOPO_server_v2.global.error.exception.StatusEnum;

public class ExpiredTokenException extends BusinessException {

    public static final BusinessException EXCEPTION = new ExpiredTokenException();

    public ExpiredTokenException(){
        super(StatusEnum.EXPIRED_TOKEN);
    }

}