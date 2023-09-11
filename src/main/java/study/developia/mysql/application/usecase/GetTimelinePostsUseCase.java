package study.developia.mysql.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.developia.mysql.domain.follow.entity.Follow;
import study.developia.mysql.domain.follow.service.FollowReadService;
import study.developia.mysql.domain.post.entity.Post;
import study.developia.mysql.domain.post.service.PostReadService;
import study.developia.mysql.util.CursorRequest;
import study.developia.mysql.util.PageCursor;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetTimelinePostsUseCase {
    private final FollowReadService followReadService;
    private final PostReadService postReadService;

    public PageCursor<Post> execute(Long memberId, CursorRequest cursorRequest) {
        /**
         * 1. memeberId -> follow 조회
         * 2. 1번 결과로 게시물 조회
         */
        List<Follow> followings = followReadService.getFollowings(memberId);
        List<Long> followingMemberIds = followings.stream().map(Follow::getId).toList();
        return postReadService.getPosts(followingMemberIds, cursorRequest);
    }

    public PageCursor<Post> executeByTimeline(Long memberId, CursorRequest cursorRequest) {
        /**
         * 1. Timeline 조회
         * 2. 1번에 해당하는 게시물 조회
         */
        List<Follow> followings = followReadService.getFollowings(memberId);
        List<Long> followingMemberIds = followings.stream().map(Follow::getId).toList();
        return postReadService.getPosts(followingMemberIds, cursorRequest);
    }
}
