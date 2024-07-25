package kr.hs.dgsw.SOPO_server_v2.domain.member.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberState {
    ACTIVE("STATE_ACTIVE"),
    DELETED("STAVE_DELETED");
    private final String key;
}
