package kr.hs.dgsw.SOPO_server_v2.global.error.custom.enroll;

import kr.hs.dgsw.SOPO_server_v2.global.error.exception.BusinessException;
import kr.hs.dgsw.SOPO_server_v2.global.error.exception.StatusEnum;

public class ENROLL_NOT_FOUND extends BusinessException {
    public static final BusinessException EXCEPTION = new ENROLL_NOT_FOUND();

    public ENROLL_NOT_FOUND() {
        super(StatusEnum.ENROLL_NOT_FOUND);}

}
