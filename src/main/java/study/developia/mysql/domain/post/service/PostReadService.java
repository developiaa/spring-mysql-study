package study.developia.mysql.domain.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import study.developia.mysql.domain.post.dto.DailyPostCount;
import study.developia.mysql.domain.post.dto.DailyPostCountRequest;
import study.developia.mysql.domain.post.entity.Post;
import study.developia.mysql.domain.post.repository.PostRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostReadService {
    private final PostRepository postRepository;

    public List<DailyPostCount> getDailyPostCount(DailyPostCountRequest request) {
        /**
         * 반환 값 -> 리스트 [작성 일자, 작성 회원, 작성 게시물 갯수]
         *
         */
        return postRepository.groupByCreatedDate(request);
    }

    public Page<Post> getPosts(Long memberId, Pageable pageable) {
        return postRepository.findAllByMemberId(memberId, pageable);
    }
}
