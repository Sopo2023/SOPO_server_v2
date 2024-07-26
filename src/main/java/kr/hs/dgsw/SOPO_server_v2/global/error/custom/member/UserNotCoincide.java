package kr.hs.dgsw.SOPO_server_v2.global.error.custom.member;

import kr.hs.dgsw.SOPO_server_v2.global.error.exception.BusinessException;
import kr.hs.dgsw.SOPO_server_v2.global.error.exception.StatusEnum;

public class UserNotCoincide extends BusinessException {
    public static final BusinessException EXCEPTION = new UserNotCoincide();

    public UserNotCoincide() {super(StatusEnum.USER_NOT_COINCIDE);}
}
