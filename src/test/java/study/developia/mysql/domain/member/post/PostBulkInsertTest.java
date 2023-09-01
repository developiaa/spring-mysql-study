package study.developia.mysql.domain.member.post;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;
import study.developia.mysql.domain.post.entity.Post;
import study.developia.mysql.domain.post.repository.PostRepository;
import study.developia.mysql.util.PostFixtureFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.IntStream;

//@Disabled
@SpringBootTest
public class PostBulkInsertTest {
    @Autowired
    private PostRepository postRepository;

    @Test
    public void bulkInsert() {
        var easyRandom = PostFixtureFactory.get(
                4L,
                LocalDate.of(1970, 1, 1),
                LocalDate.of(2022, 2, 1));

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        List<Post> posts = IntStream.range(0, 20000 * 100)
                .mapToObj(i -> easyRandom.nextObject(Post.class))
                .toList();

        stopWatch.stop();
        System.out.println("객체 생성 시간 : " + stopWatch.getTotalTimeSeconds());

        StopWatch queryStopWatch = new StopWatch();
        queryStopWatch.start();
        postRepository.bulkInsert(posts);
        queryStopWatch.stop();
        System.out.println("쿼리 생성 시간 : " + queryStopWatch.getTotalTimeSeconds());
    }
}
