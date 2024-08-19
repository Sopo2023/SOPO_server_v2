package kr.hs.dgsw.SOPO_server_v2.global.error.custom.comment;

import kr.hs.dgsw.SOPO_server_v2.global.error.exception.BusinessException;
import kr.hs.dgsw.SOPO_server_v2.global.error.exception.StatusEnum;

public class CommentNotFound extends BusinessException {
    public static final BusinessException EXCEPTION = new CommentNotFound();

    public CommentNotFound() {
        super(StatusEnum.COMMENT_NOT_FOUND);
    }

}
