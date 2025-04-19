package mycrud.bcinside.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import mycrud.bcinside.domain.LoginProcessor;
import mycrud.bcinside.domain.Member;
import mycrud.bcinside.domain.MemberDTO;
import mycrud.bcinside.domain.SessionConst;
import mycrud.bcinside.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/signup")
    public String signupForm(){
        return "/member/signup";
    }

    @PostMapping("/signup")
    public String signup(MemberDTO form){
        memberService.join(form);

        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginForm(){
        return "/member/login";
    }

    @PostMapping("/login")
    public String login(MemberDTO form, HttpServletRequest request) {
        if (memberService.login(form)) {
            // 로그인 성공하면 세션에 사용자 아이디 저장
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("userId", form.getUserId());
            return "redirect:/main";
        }
        return "/member/loginFalse";
    }


    @GetMapping("/memberList")
    @ResponseBody
    public List<Member> memberList() {
        return memberService.findMembers();
    }
}
