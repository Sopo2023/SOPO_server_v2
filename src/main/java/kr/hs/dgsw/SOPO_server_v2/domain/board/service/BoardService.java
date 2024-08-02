package kr.hs.dgsw.SOPO_server_v2.domain.board.service;

import jakarta.transaction.Transactional;
import kr.hs.dgsw.SOPO_server_v2.domain.board.dto.BoardLoadRes;
import kr.hs.dgsw.SOPO_server_v2.domain.board.dto.BoardUpdateReq;
import kr.hs.dgsw.SOPO_server_v2.domain.board.entity.BoardEntity;
import kr.hs.dgsw.SOPO_server_v2.domain.board.repository.BoardRepository;
import kr.hs.dgsw.SOPO_server_v2.domain.member.entity.MemberEntity;
import kr.hs.dgsw.SOPO_server_v2.domain.member.enums.MemberCategory;
import kr.hs.dgsw.SOPO_server_v2.global.error.custom.board.BoardNotFound;
import kr.hs.dgsw.SOPO_server_v2.global.error.custom.member.MemberNotCoincideException;
import kr.hs.dgsw.SOPO_server_v2.global.infra.security.GetCurrentMember;
import kr.hs.dgsw.SOPO_server_v2.global.response.ResponseData;
import kr.hs.dgsw.SOPO_server_v2.global.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final GetCurrentMember getCurrentMember;

    // 게시글 전체 조회
    public ResponseData<List<BoardLoadRes>> getBoards() {
        List<BoardEntity> boardList = boardRepository.findAll();
        List<BoardLoadRes> boardLoadResList = boardList.stream().map(
                BoardLoadRes::of
        ).toList();

        return ResponseData.of(HttpStatus.OK, "게시물 전체 조회 완료", boardLoadResList);
    }

    // 빈 게시글 생성
    public ResponseData<Long> createBoard() {
        MemberEntity curMember = getCurrentMember.current();

        BoardEntity board = BoardEntity.builder()
                .boardTitle(null)
                .boardContent(null)
<<<<<<< HEAD
                .boardLikeCount(0)
=======
>>>>>>> e493e579002345c8a1e3507d0ba6d7a8691fc148
                .file(null)
                .member(curMember)
                .build();

<<<<<<< HEAD
        boardRepository.save(board);

=======
>>>>>>> e493e579002345c8a1e3507d0ba6d7a8691fc148
        return ResponseData.of(HttpStatus.OK, "빈 게시물 생성 완료", board.getBoardId());
    }

    // 게시글 업데이트
<<<<<<< HEAD
    public Response updateBoard(Long boardId, BoardUpdateReq updateReq) {
=======
    public Response loadBoard(Long boardId, BoardUpdateReq updateReq) {
>>>>>>> e493e579002345c8a1e3507d0ba6d7a8691fc148
        MemberEntity curMember = getCurrentMember.current();

        BoardEntity board = boardRepository.findById(boardId)
                .orElseThrow(() -> BoardNotFound.EXCEPTION);

        // 만약 현재 로그인 유저와 Load 하려는 유저가 다르고, admin 아니라면
        if (!board.getMember().getMemberId().equals(curMember.getMemberId()) && curMember.getMemberCategory() == MemberCategory.USER) {
            throw MemberNotCoincideException.EXCEPTION;
        }

        board.update(updateReq);
<<<<<<< HEAD

=======
>>>>>>> e493e579002345c8a1e3507d0ba6d7a8691fc148
        return Response.of(HttpStatus.OK, "게시물 업데이트 완료");
    }

    // 게시글 단일 조회
    public ResponseData<BoardLoadRes> findOneBoard(Long boardId) {
        BoardEntity board = boardRepository.findById(boardId)
                .orElseThrow(() -> BoardNotFound.EXCEPTION);
        BoardLoadRes boardLoadRes = BoardLoadRes.of(board);
        return ResponseData.of(HttpStatus.OK, "게시물 단일 조회 완료", boardLoadRes);
    }

    // 게시글 삭제
    @Transactional
    public Response deleteBoard(Long boardId) {
        MemberEntity curMember = getCurrentMember.current();

        BoardEntity board = boardRepository.findById(boardId)
                .orElseThrow(() -> BoardNotFound.EXCEPTION);

        // 만약 만든 사람과 삭제하려는 사람이 일치하지 않고 admin 아니라면.. error
        if (!board.getMember().getMemberId().equals(curMember.getMemberId()) && curMember.getMemberCategory() == MemberCategory.USER) {
            throw MemberNotCoincideException.EXCEPTION;
        }

        boardRepository.deleteById(boardId);
        return Response.of(HttpStatus.OK, "게시물 삭제 완료");
    }


}