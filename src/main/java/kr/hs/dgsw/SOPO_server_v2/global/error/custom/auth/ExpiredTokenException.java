package com.b1nd.alimoserver.global.error.custom.auth;

import com.b1nd.alimoserver.global.error.exception.BusinessException;
import com.b1nd.alimoserver.global.error.exception.StatusEnum;

public class ExpiredTokenException extends BusinessException {

    public static final BusinessException EXCEPTION = new ExpiredTokenException();

    public ExpiredTokenException(){
        super(StatusEnum.EXPIRED_TOKEN);
    }

}
