package uhs.alphabet.web;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class IndexController {
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/introduction")
    public String introduction() {
        return "introduction";
    }

    @GetMapping("/history")
    public String history() {
        return "history";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }
}
