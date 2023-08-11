package study.developia.mysql.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.developia.mysql.domain.member.dto.MemberDto;
import study.developia.mysql.domain.member.dto.RegisterMemberCommand;
import study.developia.mysql.domain.member.entity.Member;
import study.developia.mysql.domain.member.repository.MemberRepository;

@RequiredArgsConstructor
@Service
public class MemberReadService {
    private final MemberRepository memberRepository;

    public MemberDto getMember(Long id) {
        Member member = memberRepository.findById(id).orElseThrow();
        return toDto(member);
    }

    public MemberDto toDto(Member member) {
        return new MemberDto(member.getId(), member.getEmail(), member.getNickname(), member.getBirthday());
    }
}
