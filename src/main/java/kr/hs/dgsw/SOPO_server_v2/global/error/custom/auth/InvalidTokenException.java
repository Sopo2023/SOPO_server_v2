package com.b1nd.alimoserver.global.error.custom.auth;

import com.b1nd.alimoserver.global.error.exception.BusinessException;
import com.b1nd.alimoserver.global.error.exception.StatusEnum;

public class InvalidTokenException extends BusinessException {

    public static final BusinessException EXCEPTION = new InvalidTokenException();

    public InvalidTokenException(){
        super(StatusEnum.INVALID_TOKEN);
    }

}