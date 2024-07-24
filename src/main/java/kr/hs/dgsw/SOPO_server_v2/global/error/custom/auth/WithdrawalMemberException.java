package com.b1nd.alimoserver.global.error.custom.auth;

import com.b1nd.alimoserver.global.error.exception.BusinessException;
import com.b1nd.alimoserver.global.error.exception.StatusEnum;

public class WithdrawalMemberException extends BusinessException {

    public static final BusinessException EXCEPTION = new WithdrawalMemberException();

    public WithdrawalMemberException(){
        super(StatusEnum.WITHDRAWAL_MEMBER);
    }

}
