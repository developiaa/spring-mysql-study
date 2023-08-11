package study.developia.mysql.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import study.developia.mysql.domain.member.dto.MemberDto;
import study.developia.mysql.domain.member.dto.RegisterMemberCommand;
import study.developia.mysql.domain.member.entity.Member;
import study.developia.mysql.domain.member.service.MemberReadService;
import study.developia.mysql.domain.member.service.MemberWriteService;

@RequiredArgsConstructor
@RestController
public class MemberController {
    private final MemberWriteService memberWriteService;
    private final MemberReadService memberReadService;

    @PostMapping("/members")
    public MemberDto register(@RequestBody RegisterMemberCommand command) {
        Member member = memberWriteService.register(command);
        return memberReadService.toDto(member);
    }

    @GetMapping("/members/{id}")
    public MemberDto getMember(@PathVariable Long id) {
        return memberReadService.getMember(id);
    }
}
