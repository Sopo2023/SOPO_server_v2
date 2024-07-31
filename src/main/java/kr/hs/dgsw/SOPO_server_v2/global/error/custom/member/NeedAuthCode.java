package kr.hs.dgsw.SOPO_server_v2.global.error.custom.member;

import kr.hs.dgsw.SOPO_server_v2.global.error.exception.BusinessException;
import kr.hs.dgsw.SOPO_server_v2.global.error.exception.StatusEnum;

public class NeedAuthCode extends BusinessException {
    public static final BusinessException EXCEPTION = new NeedAuthCode();

    public NeedAuthCode() {
        super(StatusEnum.NEED_AUTH_CODE);
    }
}
