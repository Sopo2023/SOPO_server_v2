package com.b1nd.alimoserver.global.error.custom.member;

import com.b1nd.alimoserver.global.error.exception.BusinessException;
import com.b1nd.alimoserver.global.error.exception.StatusEnum;

public class UserNotCoincideException extends BusinessException {
    public static final BusinessException EXCEPTION = new UserNotCoincideException();

    public UserNotCoincideException() {
        super(StatusEnum.USER_NOT_COINCIDE);
    }
}
