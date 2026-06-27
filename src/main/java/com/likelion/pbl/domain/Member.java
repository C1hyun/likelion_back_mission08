package com.likelion.pbl.domain;

import jakarta.persistence.*;

/**
 * [JPA 엔티티] member 테이블과 매핑
 *
 * week7 비교:
 *   Before: Role(추상) ─ Lion(studentId) / Staff(position) 상속 구조
 *   After : Member 단일 엔티티 + RoleType Enum
 *
 * studentId → Lion일 때만 값, Staff는 null
 * position  → Staff일 때만 값, Lion은 null
 */
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String major;
    private int    generation;
    private String part;

    @Enumerated(EnumType.STRING)   // DB에 "LION" / "STAFF" 문자열로 저장
    private RoleType roleType;

    private String studentId;   // Lion 전용, Staff는 null
    private String position;    // Staff 전용, Lion은 null

    // JPA 필수: 기본 생성자 (외부에서 직접 사용하지 않도록 protected)
    protected Member() {}

    // 전체 필드 생성자
    public Member(String name, String major, int generation, String part,
                  RoleType roleType, String studentId, String position) {
        this.name       = name;
        this.major      = major;
        this.generation = generation;
        this.part       = part;
        this.roleType   = roleType;
        this.studentId  = studentId;
        this.position   = position;
    }

    // ── Getters ──────────────────────────────────────────────
    public Long     getId()         { return id; }
    public String   getName()       { return name; }
    public String   getMajor()      { return major; }
    public int      getGeneration() { return generation; }
    public String   getPart()       { return part; }
    public RoleType getRoleType()   { return roleType; }
    public String   getStudentId()  { return studentId; }
    public String   getPosition()   { return position; }

    // ── 수정 메서드 (dirty checking 활용) ──────────────────────
    public void updateInfo(String major, int generation, String part) {
        this.major      = major;
        this.generation = generation;
        this.part       = part;
    }

    public void updateStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void updatePosition(String position) {
        this.position = position;
    }
}
