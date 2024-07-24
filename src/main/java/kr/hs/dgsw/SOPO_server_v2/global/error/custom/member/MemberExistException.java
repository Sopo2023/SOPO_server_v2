package kr.hs.dgsw.SOPO_server_v2.global.error.custom.member;

import kr.hs.dgsw.SOPO_server_v2.global.error.exception.BusinessException;
import kr.hs.dgsw.SOPO_server_v2.global.error.exception.StatusEnum;

public class MemberExistException extends BusinessException {
    public static final BusinessException EXCEPTION = new MemberExistException();

    public MemberExistException() {
        super(StatusEnum.MEMBER_EXISTS);
    }
}
