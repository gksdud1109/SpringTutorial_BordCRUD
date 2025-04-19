package mycrud.bcinside.service;

import mycrud.bcinside.aspects.ToLog;
import mycrud.bcinside.domain.LoginProcessor;
import mycrud.bcinside.domain.Member;
import mycrud.bcinside.domain.MemberDTO;
import mycrud.bcinside.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final LoginProcessor loginProcessor;

    public MemberService(MemberRepository memberRepository, LoginProcessor loginProcessor) {
        this.loginProcessor = loginProcessor;
        this.memberRepository = memberRepository;
    }

    public Long join(MemberDTO MemberDTO){
        Member member = new Member(MemberDTO.getUserId(), MemberDTO.getPassword());

        validateDuplicateMember(member);
        memberRepository.join(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByUserId(member.getUserId())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    @ToLog
    public boolean login(MemberDTO memberDTO){
        return loginProcessor.login(memberDTO);
    }

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }
}
