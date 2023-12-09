package study.developia.mysql.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.developia.mysql.domain.member.dto.MemberDto;
import study.developia.mysql.domain.member.service.MemberReadService;
import study.developia.mysql.domain.post.entity.Post;
import study.developia.mysql.domain.post.service.PostReadService;
import study.developia.mysql.domain.postlike.service.PostLikeWriteService;

@Service
@RequiredArgsConstructor
public class CreatePostLikeUseCase {
    private final PostReadService postReadService;
    private final MemberReadService memberReadService;
    private final PostLikeWriteService postLikeWriteService;

    public void execute(Long postId, Long memberId) {
        Post post = postReadService.getPost(postId);
        MemberDto member = memberReadService.getMember(memberId);
        postLikeWriteService.create(post, member);
    }
}
