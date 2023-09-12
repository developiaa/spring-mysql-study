package study.developia.mysql.domain.follow.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.developia.mysql.domain.follow.entity.Follow;
import study.developia.mysql.domain.follow.repository.FollowRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FollowReadService {
    private final FollowRepository followRepository;

    public List<Follow> getFollowings(Long memberId) {
        return followRepository.findAllByFromMemberId(memberId);
    }

    public List<Follow> getFollowers(Long memberId) {
        return followRepository.findAllByToMemberId(memberId);
    }
}
