package study.developia.mysql.application.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import study.developia.mysql.application.usecase.CreateFollowMemberUseCase;
import study.developia.mysql.application.usecase.GetFollowingMembersUseCase;
import study.developia.mysql.domain.member.dto.MemberDto;

import java.util.List;

@RequestMapping("/follow")
@RequiredArgsConstructor
@RestController
public class FollowController {
    private final CreateFollowMemberUseCase createFollowMemberUseCase;
    private final GetFollowingMembersUseCase getFollowingMembersUseCase;

    @PostMapping("/{fromId}/{toId}")
    public void create(@PathVariable Long fromId, @PathVariable Long toId) {
        createFollowMemberUseCase.execute(fromId, toId);
    }

    @GetMapping("/members/{fromId}")
    public List<MemberDto> create(@PathVariable Long fromId) {
        return getFollowingMembersUseCase.execute(fromId);
    }
}
