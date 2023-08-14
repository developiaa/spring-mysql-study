package study.developia.mysql.domain.follow.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import study.developia.mysql.domain.follow.entity.Follow;
import study.developia.mysql.domain.follow.repository.FollowRepository;
import study.developia.mysql.domain.member.dto.MemberDto;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class FollowWriteService {
    private final FollowRepository followRepository;

    public void create(MemberDto fromMember, MemberDto toMember) {
        Assert.isTrue(!Objects.equals(fromMember.id(), toMember.id()), "From, To 회원이 동일합니다.");

        Follow follow = Follow.builder()
                .fromMemberId(fromMember.id())
                .toMemberId(toMember.id())
                .build();

        followRepository.save(follow);
    }
}
