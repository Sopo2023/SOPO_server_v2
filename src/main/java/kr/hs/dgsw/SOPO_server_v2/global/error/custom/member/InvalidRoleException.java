package kr.hs.dgsw.SOPO_server_v2.global.error.custom.member;

import kr.hs.dgsw.SOPO_server_v2.global.error.exception.BusinessException;
import kr.hs.dgsw.SOPO_server_v2.global.error.exception.StatusEnum;

public class InvalidRoleException extends BusinessException {
    public static final BusinessException EXCEPTION = new InvalidRoleException();

    public InvalidRoleException() {
        super(StatusEnum.ID_ALREADY_EXIST);
    }
}