package kr.hs.dgsw.SOPO_server_v2.domain.contest.service;

import jakarta.transaction.Transactional;
import kr.hs.dgsw.SOPO_server_v2.domain.board.dto.BoardLoadRes;
import kr.hs.dgsw.SOPO_server_v2.domain.contest.dto.ContestLoadRes;
import kr.hs.dgsw.SOPO_server_v2.domain.contest.dto.ContestUpdateReq;
import kr.hs.dgsw.SOPO_server_v2.domain.contest.entity.ContestEntity;
import kr.hs.dgsw.SOPO_server_v2.domain.contest.enums.ContestState;
import kr.hs.dgsw.SOPO_server_v2.domain.contest.repository.ContestRepository;
import kr.hs.dgsw.SOPO_server_v2.domain.member.entity.MemberEntity;
import kr.hs.dgsw.SOPO_server_v2.domain.member.enums.MemberCategory;
import kr.hs.dgsw.SOPO_server_v2.global.error.custom.contest.ContestNotFound;
import kr.hs.dgsw.SOPO_server_v2.global.error.custom.member.MemberNotCoincideException;
import kr.hs.dgsw.SOPO_server_v2.global.infra.security.GetCurrentMember;
import kr.hs.dgsw.SOPO_server_v2.global.response.Response;
import kr.hs.dgsw.SOPO_server_v2.global.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ContestService {

    private final ContestRepository contestRepository;
    private final GetCurrentMember getCurrentMember;

    // 대회 전체 조회
    public ResponseData<List<ContestLoadRes>> getContests(int size, int page) {
        Pageable pageable = PageRequest.of(page, size);
        List<ContestEntity> contestList = contestRepository.findAll(pageable).getContent();

        return ResponseData.of(HttpStatus.OK, "대회 전체 조회 완료", contestList.stream().map(ContestLoadRes::of).toList());
    }

    // 빈 대회 생성
    public ResponseData<Long> createContest() {
        MemberEntity curMember = getCurrentMember.current();

        ContestEntity contest = ContestEntity.builder()
                .contestMax(0)
                .contestContent(null)
                .contestPerson(0)
                .contestState(ContestState.ACTIVE)
                .contestLikeCount(0)
                .contestDateTime(null)
                .memberIdList(null)
                .file(null)
                .member(curMember)
                .build();

        contestRepository.save(contest);

        return ResponseData.of(HttpStatus.OK, "대회 생성 완료", contest.getContestId());
    }

    // 대회 업데이트
    public Response updateContest(Long contestId, ContestUpdateReq updateReq) {

        MemberEntity curMember = getCurrentMember.current();

        ContestEntity contest = contestRepository.findById(contestId)
                .orElseThrow(() -> ContestNotFound.EXCEPTION);

        checkMemberAuthority(curMember, contest);

        contest.update(updateReq);

        return Response.of(HttpStatus.OK, "대회 업데이트 완료");
    }

    // 대회 단일 조회
    public ResponseData<ContestLoadRes> findOneContest(Long contestId) {

        ContestEntity contest = contestRepository.findById(contestId)
                .orElseThrow(() -> ContestNotFound.EXCEPTION);

        ContestLoadRes contestLoadRes = ContestLoadRes.of(contest);

        return ResponseData.of(HttpStatus.OK, "대회 조회 완료", contestLoadRes);
    }

    // 대회 삭제
    public Response deleteContest(Long contestId) {

        MemberEntity curMember = getCurrentMember.current();

        ContestEntity contest = contestRepository.findById(contestId)
                .orElseThrow(() -> ContestNotFound.EXCEPTION);

        checkMemberAuthority(curMember, contest);

        contestRepository.delete(contest);
        return Response.of(HttpStatus.OK, "대회 삭제 완료");
    }

    // 대회 상태 변경
    public Response changeContestState(Long contestId) {

        MemberEntity curMember = getCurrentMember.current();

        ContestEntity contest = contestRepository.findById(contestId)
                .orElseThrow(() -> ContestNotFound.EXCEPTION);

        checkMemberAuthority(curMember, contest);

        if (contest.getContestState() == ContestState.ACTIVE) {
            contest.stateUpdateDisabled();
        } else {
            contest.stateUpdateActive();
        }

        return Response.of(HttpStatus.OK, "대회 상태 변경 성공!");
    }

    private void checkMemberAuthority(MemberEntity curMember, ContestEntity contest) {
        if (!contest.getMember().getMemberId().equals(curMember.getMemberId()) && curMember.getMemberCategory() == MemberCategory.USER) {
            throw MemberNotCoincideException.EXCEPTION;
        }
    }
}