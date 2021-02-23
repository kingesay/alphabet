package uhs.alphabet.web;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


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

    @GetMapping(value = "/api/getSVG", produces = "image/svg+xml")
    @ResponseBody
    public String getSVG3(@RequestParam("stuID") Integer stuID) {
        HashMap<Integer, String> mem = new HashMap<Integer, String>();
        mem.put(20180647, "BueVonHun");
        mem.put(20150823, "Hello_miz");
        mem.put(20160825, "ParkCH");
        String handle = "None";
        if (mem.containsKey(stuID)) {
            handle = mem.get(stuID);
        }
        String res = "<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" xmlns:svgjs=\"http://svgjs.com/svgjs\" width=\"350\" height=\"131\"><g><rect width=\"30\" height=\"40\" fill=\"#c4e693\" stroke-width=\"2\" stroke=\"#111111\" x=\"10\" y=\"15\"></rect><rect width=\"60\" height=\"40\" fill=\"#ffc519\" stroke-width=\"2\" stroke=\"#111111\" x=\"100\" y=\"15\"></rect><rect width=\"30\" height=\"60\" fill=\"#fffa78\" stroke-width=\"2\" stroke=\"#111111\" x=\"40\" y=\"35\"></rect><rect width=\"90\" height=\"20\" fill=\"#3cfbff\" stroke-width=\"2\" stroke=\"#111111\" x=\"10\" y=\"95\"></rect><rect width=\"30\" height=\"40\" fill=\"#4b4b4b\" stroke-width=\"2\" stroke=\"#111111\" x=\"70\" y=\"15\"></rect><rect width=\"30\" height=\"40\" fill=\"#ff5675\" stroke-width=\"2\" stroke=\"#111111\" x=\"130\" y=\"55\"></rect><rect width=\"30\" height=\"20\" fill=\"#c4e693\" stroke-width=\"2\" stroke=\"#111111\" x=\"100\" y=\"95\"></rect><rect width=\"30\" height=\"20\" fill=\"none\" stroke-width=\"2\" stroke=\"#111111\" x=\"40\" y=\"15\"></rect><rect width=\"30\" height=\"40\" fill=\"none\" stroke-width=\"2\" stroke=\"#111111\" x=\"10\" y=\"55\"></rect><rect width=\"60\" height=\"40\" fill=\"none\" stroke-width=\"2\" stroke=\"#111111\" x=\"70\" y=\"55\"></rect><rect width=\"30\" height=\"20\" fill=\"none\" stroke-width=\"2\" stroke=\"#111111\" x=\"130\" y=\"95\"></rect></g><text font-size=\"20\" id=\"handle\" x=\"210\" y=\"55.828125\"><tspan dy=\"26\" x=\"210\">"+handle+"</tspan></text><text font-size=\"20\" x=\"210.921875\" y=\"5.828125\"><tspan dy=\"26\" x=\"210.921875\">ALPHABET</tspan></text><rect width=\"350\" height=\"131\" fill=\"transparent\" stroke=\"#111111\" rx=\"20\" ry=\"10\"></rect></svg>";
        return res;
    }
}
