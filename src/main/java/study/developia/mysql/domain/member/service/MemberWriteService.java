package study.developia.mysql.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.developia.mysql.domain.member.dto.RegisterMemberCommand;
import study.developia.mysql.domain.member.entity.Member;
import study.developia.mysql.domain.member.entity.MemberNicknameHistory;
import study.developia.mysql.domain.member.repository.MemberNicknameHistoryRepository;
import study.developia.mysql.domain.member.repository.MemberRepository;


@RequiredArgsConstructor
@Service
public class MemberWriteService {
    private final MemberRepository memberRepository;
    private final MemberNicknameHistoryRepository memberNicknameHistoryRepository;

    @Transactional
    public Member register(RegisterMemberCommand command) {
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

        var savedMember = memberRepository.save(member);
        saveMemberNicknameHistory(savedMember);
        return savedMember;
    }

    public void changeNickname(Long memberId, String nickname) {
        /*
         * 1. 회원의 이름을 변경
         * 2. 변경 내역을 저장한다.
         */
        Member member = memberRepository.findById(memberId).orElseThrow();
        member.changeNickname(nickname);
        memberRepository.save(member);

        saveMemberNicknameHistory(member);
    }

    private void saveMemberNicknameHistory(Member member) {
        var history = MemberNicknameHistory.builder()
                .memberId(member.getId())
                .nickname(member.getNickname())
                .build();

        memberNicknameHistoryRepository.save(history);
    }
}
