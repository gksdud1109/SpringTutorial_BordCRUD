package mycrud.bcinside.domain;

import mycrud.bcinside.repository.MemberRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Optional;

@Component
@RequestScope
public class LoginProcessor {
    private String userId;
    private String password;

    private final MemberRepository memberRepository;

    public LoginProcessor(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }


    public boolean login(MemberDTO memberDTO){
        this.setUserId(memberDTO.getUserId());
        this.setPassword(memberDTO.getPassword());

        String userId=this.getUserId();
        String password=this.getPassword();

        Optional<Member> loginMember = memberRepository.findByUserId(userId);
        if(loginMember.isPresent()){
            Member member = loginMember.get();
            if(password.equals(member.getPassword()))
                return true;
        }
        return false;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
