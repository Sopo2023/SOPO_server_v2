package com.b1nd.alimoserver.global.error.custom.auth;

import com.b1nd.alimoserver.global.error.exception.BusinessException;
import com.b1nd.alimoserver.global.error.exception.StatusEnum;

public class UnableToSendEmailException extends BusinessException {
    public static final BusinessException EXCEPTION = new UnableToSendEmailException();

    public UnableToSendEmailException(){
        super(StatusEnum.UNABLE_TO_SEND_EMAIL);
    }
}
