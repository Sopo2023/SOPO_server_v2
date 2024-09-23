package kr.hs.dgsw.SOPO_server_v2.domain.contest.service;

import jakarta.transaction.Transactional;
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
import kr.hs.dgsw.SOPO_server_v2.global.page.PageRequest;
import kr.hs.dgsw.SOPO_server_v2.global.response.Response;
import kr.hs.dgsw.SOPO_server_v2.global.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ContestService {

    private final ContestRepository contestRepository;
    private final GetCurrentMember getCurrentMember;

    // 대회 전체 조회
    public ResponseData<List<ContestLoadRes>> getContests(PageRequest pageRequest) {
        List<ContestEntity> contestList = contestRepository.findAll();

        List<ContestLoadRes> contestLoadRes = contestList.stream()
                .map(ContestLoadRes::of)
                .skip((pageRequest.page() -1) * pageRequest.size())
                .limit(pageRequest.size())
                .collect(Collectors.toList());

        return ResponseData.of(HttpStatus.OK, "대회 전체 조회 완료", contestLoadRes);
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

        // 만약 현재 로그인 유저와 Load 하려는 유저가 다르다면
        if (!contest.getMember().getMemberId().equals(curMember.getMemberId()) && curMember.getMemberCategory() == MemberCategory.USER) {
            throw MemberNotCoincideException.EXCEPTION;
        }

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

        if (!contest.getMember().getMemberId().equals(curMember.getMemberId()) && curMember.getMemberCategory() == MemberCategory.USER) {
            throw MemberNotCoincideException.EXCEPTION;
        }

        contestRepository.deleteById(contestId);
        return Response.of(HttpStatus.OK, "대회 삭제 완료");
    }

    // 대회 상태 변경
    public Response changeContestState(Long contestId) {

        MemberEntity curMember = getCurrentMember.current();

        ContestEntity contest = contestRepository.findById(contestId)
                .orElseThrow(() -> ContestNotFound.EXCEPTION);

        if (!contest.getMember().getMemberId().equals(curMember.getMemberId()) && curMember.getMemberCategory() == MemberCategory.USER) {
            throw MemberNotCoincideException.EXCEPTION;
        }

        if (contest.getContestState() == ContestState.ACTIVE) {
            contest.stateUpdateDisabled();
        } else {
            contest.stateUpdateActive();
        }

        return Response.of(HttpStatus.OK, "대회 상태 변경 성공!");
    }

}