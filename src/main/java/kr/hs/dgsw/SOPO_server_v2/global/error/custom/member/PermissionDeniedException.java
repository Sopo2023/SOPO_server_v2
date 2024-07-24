package com.b1nd.alimoserver.global.error.custom.member;

import com.b1nd.alimoserver.global.error.exception.BusinessException;
import com.b1nd.alimoserver.global.error.exception.StatusEnum;

public class PermissionDeniedException extends BusinessException {
    public static final BusinessException EXCEPTION = new PermissionDeniedException();

    public PermissionDeniedException() {
        super(StatusEnum.PERMISSION_DENIED);
    }
}

