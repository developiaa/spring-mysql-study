package study.developia.mysql.domain.postlike.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.developia.mysql.domain.member.dto.MemberDto;
import study.developia.mysql.domain.post.dto.PostCommand;
import study.developia.mysql.domain.post.entity.Post;
import study.developia.mysql.domain.post.repository.PostRepository;
import study.developia.mysql.domain.postlike.entity.PostLike;
import study.developia.mysql.domain.postlike.repository.PostLikeRepository;

@RequiredArgsConstructor
@Service
public class PostLikeWriteService {
    private final PostLikeRepository postLikeRepository;

    public Long create(Post post, MemberDto memberDto) {
        PostLike postLike = PostLike.builder()
                .postId(post.getId())
                .memberId(memberDto.id())
                .build();

        return postLikeRepository.save(postLike).getId();
    }

}
