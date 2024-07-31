package kr.hs.dgsw.SOPO_server_v2.domain.like.service;

import jakarta.transaction.Transactional;
import kr.hs.dgsw.SOPO_server_v2.domain.board.entity.BoardEntity;
import kr.hs.dgsw.SOPO_server_v2.domain.board.repository.BoardRepository;
import kr.hs.dgsw.SOPO_server_v2.domain.contest.entity.ContestEntity;
import kr.hs.dgsw.SOPO_server_v2.domain.contest.repository.ContestRepository;
import kr.hs.dgsw.SOPO_server_v2.domain.like.entity.LikeEntity;
import kr.hs.dgsw.SOPO_server_v2.domain.like.enums.LikeCategory;
import kr.hs.dgsw.SOPO_server_v2.domain.like.repository.LikeRepository;
import kr.hs.dgsw.SOPO_server_v2.domain.member.entity.MemberEntity;
import kr.hs.dgsw.SOPO_server_v2.global.error.custom.board.BoardNotFound;
import kr.hs.dgsw.SOPO_server_v2.global.error.custom.contest.ContestNotFound;
import kr.hs.dgsw.SOPO_server_v2.global.infra.security.GetCurrentMember;
import kr.hs.dgsw.SOPO_server_v2.global.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class LikeService {
    // 멤버 필요
    private final BoardRepository boardRepository;
    private final ContestRepository contestRepository;
    private final LikeRepository likeRepository;
    private final GetCurrentMember getCurrentMember;

    public Response toggle(Long id, LikeCategory category) {
        MemberEntity curMember = getCurrentMember.current();

        if (category == LikeCategory.BOARD) {
            BoardEntity board = boardRepository.findById(id)
                    .orElseThrow(() -> BoardNotFound.EXCEPTION);

            Optional<LikeEntity> like = likeRepository.findByMemberAndBoard(curMember, board);

            if (like.isEmpty()) {
                addBoardLike(curMember, board);
                board.likeUpdate(1);
            } else {
                boardRepository.delete(like.get().getBoard());
                board.likeUpdate(-1);
            }
        }

        // 대회 좋아요일 때
        else if (category == LikeCategory.CONTEST) {
            // 토큰 가져오기
            ContestEntity contest = contestRepository.findById(id)
                    .orElseThrow( () -> ContestNotFound.EXCEPTION);

            Optional<LikeEntity> like = likeRepository.findByMemberAndContest(curMember, contest);

            if (like.isEmpty()) {
                addContestLike(curMember, contest);
                contest.likeUpdate(1);
            } else {
                contestRepository.delete(like.get().getContest());
                contest.likeUpdate(1);
            }
        }

        // else if (프로필) {}

        return Response.of(HttpStatus.OK, "좋아요 생성/취소 완료");



    }

    private void addBoardLike(MemberEntity member, BoardEntity board) {
        likeRepository.save(
                LikeEntity.builder()
                        .board(board)
                        .member(member)
                        .likeCategory(LikeCategory.BOARD)
                        .build()
        );
    }

    public void addContestLike(MemberEntity member, ContestEntity contest) {
        likeRepository.save(
                LikeEntity.builder()
                        .contest(contest)
                        .member(member)
                        .likeCategory(LikeCategory.CONTEST)
                        .build()
        );
    }

    // 프로필
}
