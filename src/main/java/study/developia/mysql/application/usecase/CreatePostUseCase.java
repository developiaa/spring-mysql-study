package study.developia.mysql.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.developia.mysql.domain.follow.entity.Follow;
import study.developia.mysql.domain.follow.service.FollowReadService;
import study.developia.mysql.domain.post.dto.PostCommand;
import study.developia.mysql.domain.post.service.PostWriteService;
import study.developia.mysql.domain.post.service.TimelineWriteService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CreatePostUseCase {
    private final PostWriteService postWriteService;
    private final FollowReadService followReadService;
    private final TimelineWriteService timelineWriteService;

    public Long execute(PostCommand postCommand) {
        Long postId = postWriteService.create(postCommand);

        List<Long> followerMemberIds = followReadService.getFollowers(postCommand.memberId())
                .stream()
                .map(Follow::getFromMemberId)
                .toList();

        timelineWriteService.deliveryToTimeline(postId, followerMemberIds);

        return postId;
    }
}
