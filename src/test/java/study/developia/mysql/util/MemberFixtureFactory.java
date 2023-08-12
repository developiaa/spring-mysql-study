package study.developia.mysql.util;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import study.developia.mysql.domain.member.entity.Member;

public class MemberFixtureFactory {
    public static Member create() {
        var param = new EasyRandomParameters();
        return new EasyRandom(param).nextObject(Member.class);
    }

    public static Member create(Long seed) {
        var param = new EasyRandomParameters().seed(seed);
        return new EasyRandom(param).nextObject(Member.class);
    }
}
