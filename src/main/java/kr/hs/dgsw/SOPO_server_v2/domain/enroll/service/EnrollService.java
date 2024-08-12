package kr.hs.dgsw.SOPO_server_v2.domain.enroll.service;

import jakarta.transaction.Transactional;
import kr.hs.dgsw.SOPO_server_v2.domain.contest.entity.ContestEntity;
import kr.hs.dgsw.SOPO_server_v2.domain.contest.enums.ContestState;
import kr.hs.dgsw.SOPO_server_v2.domain.contest.repository.ContestRepository;
import kr.hs.dgsw.SOPO_server_v2.domain.enroll.entity.EnrollEntity;
import kr.hs.dgsw.SOPO_server_v2.domain.enroll.repository.EnrollRepository;
import kr.hs.dgsw.SOPO_server_v2.domain.member.entity.MemberEntity;
import kr.hs.dgsw.SOPO_server_v2.domain.member.enums.MemberCategory;
import kr.hs.dgsw.SOPO_server_v2.domain.member.repository.MemberRepository;
import kr.hs.dgsw.SOPO_server_v2.global.error.custom.contest.ContestNotFound;
import kr.hs.dgsw.SOPO_server_v2.global.error.custom.enroll.ENROLL_NOT_FOUND;
import kr.hs.dgsw.SOPO_server_v2.global.error.custom.member.MemberNotCoincideException;
import kr.hs.dgsw.SOPO_server_v2.global.infra.security.GetCurrentMember;
import kr.hs.dgsw.SOPO_server_v2.global.response.Response;
import kr.hs.dgsw.SOPO_server_v2.global.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class EnrollService {

    private final ContestRepository contestRepository;
    private final GetCurrentMember getCurrentMember;
    private final EnrollRepository enrollRepository;
    private final MemberRepository memberRepository;

    // 신청 기능
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
                // enroll 찾기
                return Response.of(HttpStatus.OK, "신청 성공");
            }
        }
        else {
            enrollRepository.delete(enroll.get());

            return Response.of(HttpStatus.OK, "신청 취소 성공");
        }

//        if (enroll.isPresent()) {
//            enrollRepository.delete(enroll.get());
//            return Response.of(HttpStatus.OK, "신청 취소 성공");
//        }
//
//        if (contest.getContestState() == ContestState.DISABLED) {
//            return Response.of(HttpStatus.OK, "신청이 마감되었습니다.");
//        }
//        else {
//            enrollContest(curMember, contest);
//            return Response.of(HttpStatus.OK, "신청 성공");
//        }
    }

    public void enrollContest(MemberEntity member, ContestEntity contest) {
        enrollRepository.save(
                EnrollEntity.builder()
                        .contest(contest)
                        .member(member)
                        .build()
        );
    }

    // 허락 기능
    public Response allowContest(Long contestId, String memberId) { // 대회, 허락받은 사람

        MemberEntity curMember = getCurrentMember.current();


        ContestEntity contest = contestRepository.findById(contestId)
                .orElseThrow(() -> ContestNotFound.EXCEPTION);

        MemberEntity member = memberRepository.findByMemberId(memberId);

        // 대회가 이미 닫혀있다면?
        if (contest.getContestState() == ContestState.DISABLED) {
            return Response.of(HttpStatus.OK, "허락할 수 없습니다.");
        }

        if (!contest.getMember().getMemberId().equals(curMember.getMemberId()) && curMember.getMemberCategory() == MemberCategory.USER) {
            throw MemberNotCoincideException.EXCEPTION;
        }


        // enroll 찾기
        Optional<EnrollEntity> enroll = enrollRepository.findByMemberAndContest(member, contest);

        System.out.println(enroll);

        // enroll 삭제
        enroll.ifPresent(enrollRepository::delete);

        if (enroll.isEmpty()) {
            throw ENROLL_NOT_FOUND.EXCEPTION;
        }

        contest.addContestPerson(1);

        if (contest.getContestMax().equals(contest.getContestPerson())) {
            contest.stateUpdateDisabled();
        }

        // member 정보 넣기
        contest.addAllowMember(member);

        return Response.of(HttpStatus.OK, "대회 신청 허락 완료");
    }

    // 거절 기능
    public Response refuseContest(Long contestId, String memberId) {

        MemberEntity curMember = getCurrentMember.current();

        ContestEntity contest = contestRepository.findById(contestId)
                .orElseThrow(() -> ContestNotFound.EXCEPTION);

        MemberEntity member = memberRepository.findByMemberId(memberId);

        if (!contest.getMember().getMemberId().equals(curMember.getMemberId()) && curMember.getMemberCategory() == MemberCategory.USER) {
            throw MemberNotCoincideException.EXCEPTION;
        }

        if (contest.getContestMax().equals(contest.getContestPerson())) {
            contest.stateUpdateDisabled();
        }

        // enroll 찾기
        Optional<EnrollEntity> enroll = enrollRepository.findByMemberAndContest(member, contest);

        // enroll 삭제
        enroll.ifPresent(enrollRepository::delete);

        return Response.of(HttpStatus.OK, "대회 신청 거절 완료");
    }

    // 신청한 사람 보여주는 기능
    public ResponseData<List<String>> showEnrollPeople(Long contestId) {
        MemberEntity curMember = getCurrentMember.current();

        ContestEntity contest = contestRepository.findById(contestId)
                .orElseThrow(() -> ContestNotFound.EXCEPTION);

        List<EnrollEntity> enrollList = enrollRepository.findByContest(contest);

        List<String> memberNameList = new ArrayList<>();
        for (EnrollEntity enroll : enrollList) {
            memberNameList.add(enroll.getMember().getMemberName());
        }

        if (!contest.getMember().getMemberId().equals(curMember.getMemberId()) && curMember.getMemberCategory() == MemberCategory.USER) {
            throw MemberNotCoincideException.EXCEPTION;
        }

        return ResponseData.of(HttpStatus.OK, "신청한 사람 조회 성공", memberNameList);
    }

}
