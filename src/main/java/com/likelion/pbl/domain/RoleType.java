package com.likelion.pbl.domain;

/**
 * [역할 Enum]
 * week7의 Role → Lion/Staff 상속 구조를 단일 Enum으로 간소화.
 * DB의 role_type 컬럼에 "LION" 또는 "STAFF" 문자열로 저장된다.
 */
public enum RoleType {

    LION("아기사자"),
    STAFF("운영진");

    private final String displayName;

    RoleType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
