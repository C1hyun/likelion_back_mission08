package com.likelion.pbl.service;

import com.likelion.pbl.domain.Member;
import com.likelion.pbl.domain.RoleType;
import com.likelion.pbl.dto.*;
import com.likelion.pbl.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional   // 클래스 전체 트랜잭션 적용 (수정 메서드의 dirty checking을 위해 필수)
public class MemberService {

    private final MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    // ── 생성 ─────────────────────────────────────────────────

    /**
     * Lion 등록. 이름 중복 시 null 반환.
     * save() 반환값 사용 → id가 채워진 Member를 받는다.
     */
    public MemberResponse createLion(LionCreateRequest req) {
        if (repository.existsByName(req.getName())) return null;

        Member member = new Member(
                req.getName(), req.getMajor(), req.getGeneration(), req.getPart(),
                RoleType.LION, req.getStudentId(), null);

        Member saved = repository.save(member);
        return MemberResponse.from(saved);
    }

    /**
     * Staff 등록. 이름 중복 시 null 반환.
     */
    public MemberResponse createStaff(StaffCreateRequest req) {
        if (repository.existsByName(req.getName())) return null;

        Member member = new Member(
                req.getName(), req.getMajor(), req.getGeneration(), req.getPart(),
                RoleType.STAFF, null, req.getPosition());

        Member saved = repository.save(member);
        return MemberResponse.from(saved);
    }

    // ── 조회 ─────────────────────────────────────────────────

    @Transactional(readOnly = true)
    public List<MemberResponse> findAll() {
        return repository.findAll().stream()
                .map(MemberResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public MemberResponse findById(Long id) {
        return repository.findById(id)
                .map(MemberResponse::from)
                .orElse(null);
    }

    // ── 수정 ─────────────────────────────────────────────────

    /**
     * Lion 수정. id로 조회 후 필드 변경 → dirty checking으로 UPDATE 자동 실행.
     * 없으면 null 반환.
     */
    public MemberResponse updateLion(Long id, LionUpdateRequest req) {
        Member member = repository.findById(id).orElse(null);
        if (member == null) return null;

        member.updateInfo(req.getMajor(), req.getGeneration(), req.getPart());
        member.updateStudentId(req.getStudentId());
        // @Transactional → 트랜잭션 종료 시 변경 감지(dirty checking)로 UPDATE 자동 실행
        return MemberResponse.from(member);
    }

    /**
     * Staff 수정. 없으면 null 반환.
     */
    public MemberResponse updateStaff(Long id, StaffUpdateRequest req) {
        Member member = repository.findById(id).orElse(null);
        if (member == null) return null;

        member.updateInfo(req.getMajor(), req.getGeneration(), req.getPart());
        member.updatePosition(req.getPosition());
        return MemberResponse.from(member);
    }

    // ── 삭제 ─────────────────────────────────────────────────

    /**
     * id로 삭제. 없으면 false 반환.
     */
    public boolean deleteMember(Long id) {
        if (!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }
}
