package study.developia.mysql.domain.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.developia.mysql.domain.post.dto.PostCommand;
import study.developia.mysql.domain.post.entity.Post;
import study.developia.mysql.domain.post.repository.PostRepository;

@RequiredArgsConstructor
@Service
public class PostWriteService {
    private final PostRepository postRepository;

    public Long create(PostCommand command) {
        Post post = Post.builder()
                .memberId(command.memberId())
                .contents(command.contents())
                .build();

        return postRepository.save(post).getId();
    }

    @Transactional
    public void likePost(Long postId) {
        Post post = postRepository.findById(postId, true).orElseThrow();
        post.incrementLikeCount();
        postRepository.save(post);
    }

    public void likePostByOptimisticLock(Long postId) {
        Post post = postRepository.findById(postId, false).orElseThrow();
        post.incrementLikeCount();
        postRepository.save(post);
    }
}
