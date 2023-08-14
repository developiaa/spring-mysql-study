package study.developia.mysql.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.developia.mysql.domain.follow.entity.Follow;
import study.developia.mysql.domain.follow.service.FollowReadService;
import study.developia.mysql.domain.member.dto.MemberDto;
import study.developia.mysql.domain.member.service.MemberReadService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetFollowingMembersUseCase {
    private final MemberReadService memberReadService;
    private final FollowReadService followReadService;

    public List<MemberDto> execute(Long memberId) {
        /**
         * 1. fromMemberId = memberId -> Follow List
         * 2. 1번을 순회하면서 회원정보를 찾으면 된다.
         */
        List<Follow> followings = followReadService.getFollowings(memberId);
        List<Long> followingMemberIds = followings.stream().map(Follow::getToMemberId).toList();
        return memberReadService.getMembers(followingMemberIds);
    }
}
