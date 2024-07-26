package kr.hs.dgsw.SOPO_server_v2.domain.member.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberState {
    ACTIVE("ROLE_ACTIVE"),
    DELETED("ROLE_DELETED");
    private final String key;
}
