package uhs.alphabet.web;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;


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

    @GetMapping("/api/getSVG")
    public ModelAndView getSVG() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/img/abcblock.svg");
        return mv;
    }
}
