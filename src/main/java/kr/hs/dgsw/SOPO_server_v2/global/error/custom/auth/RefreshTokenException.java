package kr.hs.dgsw.SOPO_server_v2.global.error.custom.auth;

import com.b1nd.alimoserver.global.error.exception.BusinessException;
import com.b1nd.alimoserver.global.error.exception.StatusEnum;

public class RefreshTokenException extends BusinessException {

    public static final BusinessException EXCEPTION = new RefreshTokenException();

    public RefreshTokenException(){
        super(StatusEnum.REFRESH_TOKEN_NOT_FOUND);
    }

}
