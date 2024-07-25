package kr.hs.dgsw.SOPO_server_v2.global.error.custom.member;

import kr.hs.dgsw.SOPO_server_v2.global.error.exception.BusinessException;
import kr.hs.dgsw.SOPO_server_v2.global.error.exception.StatusEnum;

public class MemberNotExistException extends BusinessException {
    public static final BusinessException EXCEPTION = new MemberNotExistException();

    public MemberNotExistException() {
        super(StatusEnum.MEMBER_NOT_EXISTS);
    }
}
