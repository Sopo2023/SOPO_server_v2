package kr.hs.dgsw.SOPO_server_v2.global.error.custom.member;


import kr.hs.dgsw.SOPO_server_v2.global.error.exception.BusinessException;
import kr.hs.dgsw.SOPO_server_v2.global.error.exception.StatusEnum;

public class MemberNotFoundException extends BusinessException {
    public static final BusinessException EXCEPTION = new MemberNotFoundException();

    public MemberNotFoundException() {
        super(StatusEnum.MEMBER_NOT_FOUND);
    }
}
