package kr.hs.dgsw.SOPO_server_v2.global.error.custom.auth;

import kr.hs.dgsw.SOPO_server_v2.global.error.exception.BusinessException;
import kr.hs.dgsw.SOPO_server_v2.global.error.exception.StatusEnum;

public class WrongPasswordException extends BusinessException {

    public static final BusinessException EXCEPTION = new WrongPasswordException();

    public WrongPasswordException(){
        super(StatusEnum.WRONG_PASSWORD);
    }

}

