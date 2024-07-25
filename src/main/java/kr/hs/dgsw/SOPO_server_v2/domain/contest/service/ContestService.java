package kr.hs.dgsw.SOPO_server_v2.domain.contest.service;

import jakarta.transaction.Transactional;
import kr.hs.dgsw.SOPO_server_v2.domain.contest.dto.ContestLoadRes;
import kr.hs.dgsw.SOPO_server_v2.domain.contest.dto.ContestUpdateReq;
import kr.hs.dgsw.SOPO_server_v2.domain.contest.entity.ContestEntity;
import kr.hs.dgsw.SOPO_server_v2.domain.contest.enums.ContestState;
import kr.hs.dgsw.SOPO_server_v2.domain.contest.repository.ContestRepository;
import kr.hs.dgsw.SOPO_server_v2.global.error.custom.contest.ContestNotFound;
import kr.hs.dgsw.SOPO_server_v2.global.response.Response;
import kr.hs.dgsw.SOPO_server_v2.global.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ContestService { // 대회 전환 필요 -> ACTIVE
    private final ContestRepository contestRepository;

    // 대회 전체 조회
    public ResponseData<List<ContestLoadRes>> getContests() {
        List<ContestEntity> contestList = contestRepository.findAll();
        List<ContestLoadRes> contestLoadRes = contestList.stream().map(
                ContestLoadRes :: of
        ).toList();

        return ResponseData.of(HttpStatus.OK, "대회 전체 조회 완료", contestLoadRes);
    }

    // 빈 대회 생성
    public ResponseData<Long> createContest() {
        // 토큰 정보 가져오기
        ContestEntity contest = ContestEntity.builder()
                // .member()
                .build();
        return ResponseData.of(HttpStatus.OK, "대회 생성 완료", contest.getContestId());
    }

    // 대회 업데이트
    public Response loadContest(Long contestId, ContestUpdateReq updateReq) {
            ContestEntity contest = contestRepository.findById(contestId)
                    .orElseThrow(() -> ContestNotFound.EXCEPTION);

            // 만약 현재 로그인 유저와 Load 하려는 유저가 다르다면

            contest.update(updateReq);
            ContestLoadRes contestLoadRes = ContestLoadRes.of(contest);

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
        // 토큰 정보 받아오기
        ContestEntity contest = contestRepository.findById(contestId)
                .orElseThrow(() -> ContestNotFound.EXCEPTION);
        // 만약 만든 사람과 삭제하려는 사람이 일치하지 않고 admin이 아니라면.. error

        contestRepository.deleteById(contestId);
        return Response.of(HttpStatus.OK, "대회 삭제 완료");
    }

    // 대회 상태 변경
    public Response changeContestState(Long contestId) {
        // 토큰 정보 받아오기
        ContestEntity contest = contestRepository.findById(contestId)
                .orElseThrow(() -> ContestNotFound.EXCEPTION);
        // 만약 만든 사람과 변경하려는 사람이 일치하지 않고 admin이 아니라면.. error

        if (contest.getContestState() == ContestState.ACTIVE) {
            contest.stateUpdateDisabled();
        } else {
            contest.stateUpdateActive();
        }

        return Response.of(HttpStatus.OK, "대회 상태 변경 성공!");
    }

}
