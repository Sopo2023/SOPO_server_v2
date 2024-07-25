package kr.hs.dgsw.SOPO_server_v2.global.error.custom.auth;


import kr.hs.dgsw.SOPO_server_v2.global.error.exception.BusinessException;
import kr.hs.dgsw.SOPO_server_v2.global.error.exception.StatusEnum;

public class RefreshTokenException extends BusinessException {

    public static final BusinessException EXCEPTION = new RefreshTokenException();

    public RefreshTokenException(){
        super(StatusEnum.REFRESH_TOKEN_NOT_FOUND);
    }

}
