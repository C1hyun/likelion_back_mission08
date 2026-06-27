package com.likelion.pbl.dto;

import com.likelion.pbl.domain.Member;

/**
 * [통합 응답 DTO]
 * week7의 LionResponse + StaffResponse → MemberResponse 하나로 통합.
 *
 * Lion  → position  null
 * Staff → studentId null
 * 역할이 다른 필드 1개 차이이므로 null 허용이 합리적인 트레이드오프.
 */
public class MemberResponse {

    private Long   id;
    private String name;
    private String major;
    private int    generation;
    private String part;
    private String roleName;
    private String studentId;   // Lion 전용 (Staff는 null)
    private String position;    // Staff 전용 (Lion은 null)

    private MemberResponse() {}

    /** Member 엔티티 → MemberResponse 변환 */
    public static MemberResponse from(Member member) {
        MemberResponse res = new MemberResponse();
        res.id          = member.getId();
        res.name        = member.getName();
        res.major       = member.getMajor();
        res.generation  = member.getGeneration();
        res.part        = member.getPart();
        res.roleName    = member.getRoleType().getDisplayName();
        res.studentId   = member.getStudentId();
        res.position    = member.getPosition();
        return res;
    }

    public Long   getId()         { return id; }
    public String getName()       { return name; }
    public String getMajor()      { return major; }
    public int    getGeneration() { return generation; }
    public String getPart()       { return part; }
    public String getRoleName()   { return roleName; }
    public String getStudentId()  { return studentId; }
    public String getPosition()   { return position; }
}
