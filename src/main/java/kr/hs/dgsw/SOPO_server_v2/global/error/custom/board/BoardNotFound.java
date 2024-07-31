package kr.hs.dgsw.SOPO_server_v2.global.error.custom.board;

import kr.hs.dgsw.SOPO_server_v2.global.error.exception.BusinessException;
import kr.hs.dgsw.SOPO_server_v2.global.error.exception.StatusEnum;

public class BoardNotFound extends BusinessException {
    public static final BusinessException EXCEPTION = new BoardNotFound();

    public BoardNotFound(){
        super(StatusEnum.BOARD_NOT_FOUND);
    }
}
