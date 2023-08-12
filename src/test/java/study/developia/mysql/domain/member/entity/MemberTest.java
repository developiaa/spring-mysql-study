package study.developia.mysql.domain.member.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import study.developia.mysql.util.MemberFixtureFactory;

class MemberTest {

    @DisplayName("회원은 닉네임을 변경할 수 있다.")
    @Test
    void testChangeNickname() {
        //ObjectMother
//        LongStream.range(0, 10)
//                .mapToObj(MemberFixtureFactory::create)
//                .forEach(member -> {
//                    System.out.println("member.getNickname() = " + member.getNickname());
//                });

        Member member = MemberFixtureFactory.create();
        String expected = "pnu";
        member.changeNickname(expected);

        Assertions.assertEquals(expected, member.getNickname());
    }

    @DisplayName("회원의 닉네임은 10자를 초과할 수 없다.")
    @Test
    void testNicknameMaxLength() {
        Member member = MemberFixtureFactory.create();
        String overMaxLength = "pnupnupnupnu";

        Assertions.assertThrows(IllegalArgumentException.class, () -> member.changeNickname(overMaxLength));
    }

}
