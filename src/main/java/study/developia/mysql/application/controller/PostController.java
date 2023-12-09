package study.developia.mysql.application.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import study.developia.mysql.application.usecase.CreatePostLikeUseCase;
import study.developia.mysql.application.usecase.CreatePostUseCase;
import study.developia.mysql.application.usecase.GetTimelinePostsUseCase;
import study.developia.mysql.domain.member.dto.PostDto;
import study.developia.mysql.domain.post.dto.DailyPostCount;
import study.developia.mysql.domain.post.dto.DailyPostCountRequest;
import study.developia.mysql.domain.post.dto.PostCommand;
import study.developia.mysql.domain.post.entity.Post;
import study.developia.mysql.domain.post.service.PostReadService;
import study.developia.mysql.domain.post.service.PostWriteService;
import study.developia.mysql.util.CursorRequest;
import study.developia.mysql.util.PageCursor;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostWriteService postWriteService;
    private final PostReadService postReadService;
    private final GetTimelinePostsUseCase getTimelinePostsUseCase;
    private final CreatePostUseCase createPostUseCase;
    private final CreatePostLikeUseCase createPostLikeUseCase;

    @PostMapping
    public Long create(PostCommand command) {
        return createPostUseCase.execute(command);
//        return postWriteService.create(command);
    }

    @GetMapping("/daily-post-counts")
    public List<DailyPostCount> getDailyPostCounts(DailyPostCountRequest request) {
        return postReadService.getDailyPostCount(request);
    }

    @GetMapping("/members/{memberId}")
    public Page<PostDto> getPosts(@PathVariable Long memberId, Pageable pageable) {
        return postReadService.getPosts(memberId, pageable);
    }

    @GetMapping("/members/{memberId}/by-cursor")
    public PageCursor<Post> getPosts(@PathVariable Long memberId, CursorRequest cursorRequest) {
        return postReadService.getPosts(memberId, cursorRequest);
    }

    @GetMapping("/members/{memberId}/timeline")
    public PageCursor<Post> getTimeline(@PathVariable Long memberId, CursorRequest cursorRequest) {
        return getTimelinePostsUseCase.executeByTimeline(memberId, cursorRequest);
    }

    @PostMapping("/{postId}/like/v1")
    public void likePost(@PathVariable Long postId) {
//        postWriteService.likePost(postId);
        postWriteService.likePostByOptimisticLock(postId);
    }

    @PostMapping("/{postId}/like/v2")
    public void likePost(@PathVariable Long postId, @RequestParam Long memberId) {
        createPostLikeUseCase.execute(postId, memberId);
    }

}
