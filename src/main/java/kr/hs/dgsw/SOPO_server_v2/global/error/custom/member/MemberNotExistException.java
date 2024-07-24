package com.b1nd.alimoserver.global.error.custom.member;

import com.b1nd.alimoserver.global.error.exception.BusinessException;
import com.b1nd.alimoserver.global.error.exception.StatusEnum;

public class MemberNotExistException extends BusinessException {
    public static final BusinessException EXCEPTION = new MemberExistException();

    public MemberNotExistException() {
        super(StatusEnum.MEMBER_NOT_EXISTS);
    }
}
