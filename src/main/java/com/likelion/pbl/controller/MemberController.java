package com.likelion.pbl.controller;

import com.likelion.pbl.dto.*;
import com.likelion.pbl.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * [MemberController] week8
 * week7과의 차이: 단건 조회·수정·삭제가 {name} → {id}(DB 기본키)로 변경
 * 응답 타입: MemberResponse 하나로 통일 (LionResponse/StaffResponse 제거)
 */
@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // ── GET /members ──────────────────────────────────────────
    @GetMapping
    public ResponseEntity<List<MemberResponse>> getAllMembers() {
        return ResponseEntity.ok(memberService.findAll());
    }

    // ── GET /members/{id} ─────────────────────────────────────
    @GetMapping("/{id}")
    public ResponseEntity<MemberResponse> getMember(@PathVariable Long id) {
        MemberResponse response = memberService.findById(id);
        if (response == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(response);
    }

    // ── POST /members/lions ───────────────────────────────────
    @PostMapping("/lions")
    public ResponseEntity<MemberResponse> createLion(@RequestBody LionCreateRequest req) {
        MemberResponse response = memberService.createLion(req);
        if (response == null) return ResponseEntity.status(409).build();
        return ResponseEntity.status(201).body(response);
    }

    // ── POST /members/staffs ──────────────────────────────────
    @PostMapping("/staffs")
    public ResponseEntity<MemberResponse> createStaff(@RequestBody StaffCreateRequest req) {
        MemberResponse response = memberService.createStaff(req);
        if (response == null) return ResponseEntity.status(409).build();
        return ResponseEntity.status(201).body(response);
    }

    // ── PUT /members/lions/{id} ───────────────────────────────
    @PutMapping("/lions/{id}")
    public ResponseEntity<MemberResponse> updateLion(
            @PathVariable Long id,
            @RequestBody LionUpdateRequest req) {
        MemberResponse response = memberService.updateLion(id, req);
        if (response == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(response);
    }

    // ── PUT /members/staffs/{id} ──────────────────────────────
    @PutMapping("/staffs/{id}")
    public ResponseEntity<MemberResponse> updateStaff(
            @PathVariable Long id,
            @RequestBody StaffUpdateRequest req) {
        MemberResponse response = memberService.updateStaff(id, req);
        if (response == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(response);
    }

    // ── DELETE /members/{id} ──────────────────────────────────
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        if (!memberService.deleteMember(id)) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }
}
