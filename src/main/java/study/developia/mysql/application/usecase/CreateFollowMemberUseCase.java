package study.developia.mysql.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.developia.mysql.domain.follow.service.FollowWriteService;
import study.developia.mysql.domain.member.dto.MemberDto;
import study.developia.mysql.domain.member.service.MemberReadService;

@RequiredArgsConstructor
@Service
public class CreateFollowMemberUseCase {
    // UseCase는 가능한 로직이 없어야 함
    // 도메인 서비스의 흐름만 제어

    private final MemberReadService memberReadService;
    private final FollowWriteService followWriteService;

    public void execute(Long fromMemberId, Long toMemberId) {
        /**
         * 1. 입력받은 memberId로 회원조회
         * 2. FollowWriteService.create()
         */
        MemberDto fromMember = memberReadService.getMember(fromMemberId);
        MemberDto toMember = memberReadService.getMember(toMemberId);
        followWriteService.create(fromMember, toMember);
    }
}
