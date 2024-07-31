package kr.hs.dgsw.SOPO_server_v2.global.error.custom.contest;

import kr.hs.dgsw.SOPO_server_v2.global.error.exception.BusinessException;
import kr.hs.dgsw.SOPO_server_v2.global.error.exception.StatusEnum;

public class ContestNotFound extends BusinessException {
    public static final BusinessException EXCEPTION = new ContestNotFound();

    public ContestNotFound(){
        super(StatusEnum.CONTEST_NOT_FOUND);
    }
}
