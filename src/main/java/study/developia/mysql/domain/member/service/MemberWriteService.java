package study.developia.mysql.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.developia.mysql.domain.member.dto.RegisterMemberCommand;
import study.developia.mysql.domain.member.entity.Member;
import study.developia.mysql.domain.member.repository.MemberRepository;

@RequiredArgsConstructor
@Service
public class MemberWriteService {
    private final MemberRepository memberRepository;

    public Member create(RegisterMemberCommand command) {
        /**
         * 목표 - 회원정보(이메일, 닉네임, 생년월일)를 등록한다.
         *     - 닉네임은 10자를 넘길 수 없다.
         *
         * 파라미터 - memberRegisterCommand
         * val member = Membmer.of(memberRegisterCommand)
         * memberRepository.save(member)
         */

        Member member = Member.builder()
                .nickname(command.nickname())
                .email(command.email())
                .birthday(command.birthday())
                .build();

        return memberRepository.save(member);
    }
}
