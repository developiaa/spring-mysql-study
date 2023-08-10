package study.developia.mysql.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import study.developia.mysql.domain.member.dto.RegisterMemberCommand;
import study.developia.mysql.domain.member.entity.Member;
import study.developia.mysql.domain.member.service.MemberWriteService;

@RequiredArgsConstructor
@RestController
public class MemberController {
    private final MemberWriteService memberWriteService;

    @PostMapping("/members")
    public Member register(@RequestBody RegisterMemberCommand command) {
        return memberWriteService.create(command);
    }
}
