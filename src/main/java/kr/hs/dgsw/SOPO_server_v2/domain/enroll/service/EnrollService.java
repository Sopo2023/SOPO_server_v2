package kr.hs.dgsw.SOPO_server_v2.domain.enroll.service;

import jakarta.transaction.Transactional;
import kr.hs.dgsw.SOPO_server_v2.domain.contest.entity.ContestEntity;
import kr.hs.dgsw.SOPO_server_v2.domain.contest.enums.ContestState;
import kr.hs.dgsw.SOPO_server_v2.domain.contest.repository.ContestRepository;
import kr.hs.dgsw.SOPO_server_v2.domain.enroll.entity.EnrollEntity;
import kr.hs.dgsw.SOPO_server_v2.domain.enroll.repository.EnrollRepository;
import kr.hs.dgsw.SOPO_server_v2.domain.member.entity.MemberEntity;
import kr.hs.dgsw.SOPO_server_v2.global.error.custom.contest.ContestNotFound;
import kr.hs.dgsw.SOPO_server_v2.global.infra.security.GetCurrentMember;
import kr.hs.dgsw.SOPO_server_v2.global.response.Response;
import kr.hs.dgsw.SOPO_server_v2.global.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class EnrollService {

    private final ContestRepository contestRepository;
    private final GetCurrentMember getCurrentMember;
    private final EnrollRepository enrollRepository;

    // 대회 신청하는 기능
    public Response toggle(Long contestId) {

        MemberEntity curMember = getCurrentMember.current();

        ContestEntity contest = contestRepository.findById(contestId)
                .orElseThrow(() -> ContestNotFound.EXCEPTION);

        Optional<EnrollEntity> enroll = enrollRepository.findByMemberAndContest(curMember, contest);

        if (enroll.isEmpty()) {
            enrollContest(curMember, contest);

            if (contest.getContestState() == ContestState.DISABLED) {

                return Response.of(HttpStatus.OK, "신청이 마감되었습니다.");

            }
            else {
                contest.addContestPerson(1);
                if (contest.getContestMax().equals(contest.getContestPerson())) {
                    contest.stateUpdateDisabled();
                }
                return Response.of(HttpStatus.OK, "신청 성공");
            }

        }
        else {
            enrollRepository.delete(enroll.get());
            contest.addContestPerson(-1);

            return Response.of(HttpStatus.OK, "신청 취소 성공");
        }

    }

    private void enrollContest(MemberEntity member, ContestEntity contest) {
        enrollRepository.save(
                EnrollEntity.builder()
                        .contest(contest)
                        .member(member)
                        .build()
        );
    }

    // 대회 허락하는 기능 신청 허락 성공, 신청 거절 성공 -> 이때 쟤네 삭제하는 것도 만들어야 함.

    // 신청한 사람 보여주는 기능 -> 이거 신청한 시간이랑 같이 보여줘야 될 듯 -> 대회 id 넘겨받기 memberid 리스트로 넘겨주기

}
