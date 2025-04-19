package mycrud.bcinside.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @GetMapping("/main")
    public String loginPage(HttpSession session,
                            Model model) {
        String userId = (String) session.getAttribute("userId");

        if (userId == null) {
            return "redirect:/";
        }

        model.addAttribute("userId", userId);
        return "main";
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }
}
