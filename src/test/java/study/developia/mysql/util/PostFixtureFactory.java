package study.developia.mysql.util;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import study.developia.mysql.domain.post.entity.Post;

import java.time.LocalDate;

import static org.jeasy.random.FieldPredicates.*;

public class PostFixtureFactory {
    public static EasyRandom get(Long memberId, LocalDate firstDate, LocalDate lastDate) {
        var idIdPredicate = named("id")
                .and(ofType(Long.class))
                .and(inClass(Post.class));

        var memberIdPredicate = named("memberId")
                .and(ofType(Long.class))
                .and(inClass(Post.class));

        var param = new EasyRandomParameters()
                .excludeField(idIdPredicate)
                .dateRange(firstDate, lastDate)
                .randomize(memberIdPredicate, ()->memberId);

        return new EasyRandom(param);
    }

}
