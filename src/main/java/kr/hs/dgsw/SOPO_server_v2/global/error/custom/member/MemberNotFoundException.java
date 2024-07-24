package com.b1nd.alimoserver.global.error.custom.member;

import com.b1nd.alimoserver.global.error.exception.BusinessException;
import com.b1nd.alimoserver.global.error.exception.StatusEnum;

public class MemberNotFoundException extends BusinessException {
    public static final BusinessException EXCEPTION = new MemberNotFoundException();

    public MemberNotFoundException() {
        super(StatusEnum.USER_NOT_FOUND);
    }
}
