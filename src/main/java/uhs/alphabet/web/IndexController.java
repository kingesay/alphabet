package uhs.alphabet.web;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.reactive.function.client.WebClient;

import uhs.alphabet.domain.dto.BoardDto;
import uhs.alphabet.domain.dto.PersonDto;
import uhs.alphabet.domain.service.BoardService;
import uhs.alphabet.domain.service.PersonService;

import java.io.*;
import java.util.*;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PersonService personService;
    private  final BoardService boardService;

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

    @GetMapping("/mirror")
    public String mirror() { return "mirror"; }

    @GetMapping("/post")
    public String write() { return "post"; }

    @PostMapping("/post")
    public String write(BoardDto boardDto) {
        boardService.saveBoard(boardDto);
        return "redirect:/board";
    }


    @GetMapping("/testPerson")
    public String test(Model model) {
        PersonDto personDto;
        try {
            personDto = personService.getPerson();
        } catch (Exception e) {
            return e.toString();
        }
        model.addAttribute("person", personDto);
        return "test";
    }

    @GetMapping("/board")
    public String list(Model model) {
        List<BoardDto> boardList = boardService.getBoardList();

        model.addAttribute("boardList", boardList);
        return "board";
    }

    @GetMapping("/board/{no}")
    public String detail(@PathVariable("no") Long no, Model model) {
        BoardDto boardDto = boardService.getBoard(no);
        model.addAttribute("board", boardDto);

        return "boardDetail";
    }

    @RestController
    public class apiControl {
        @RequestMapping(value = "/api/getSVG", method = RequestMethod.GET, produces = "image/svg+xml", params = "stuID")
        @ResponseBody
        public ResponseEntity<String> getSVG(@RequestParam("stuID") String stuID, Model model) {
            System.out.println(stuID);
            List<PersonDto> personDtos = personService.searchPerson(stuID);

            String handle = "None";
            String name = "None";
            if (!personDtos.isEmpty()) {
                handle = personDtos.get(0).getHandle();
                name = personDtos.get(0).getName();
            }

            return new ResponseEntity<String>("<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" xmlns:svgjs=\"http://svgjs.com/svgjs\" width=\"353\" height=\"134\">\n" +
                    "<g>\n" +
                    "\t<rect width=\"30\" height=\"40\" fill=\"#c4e693\" stroke-width=\"2\" stroke=\"#111111\" x=\"10\" y=\"15\" opacity=\"0\">\n" +
                    "\t  <animate id=\"animation1\" attributeName=\"opacity\" values=\"0;1\" dur=\"0.4s\" fill=\"freeze\"></animate>\n" +
                    "\t</rect>\n" +
                    "\t<rect width=\"60\" height=\"40\" fill=\"#ffc519\" stroke-width=\"2\" stroke=\"#111111\" x=\"100\" y=\"15\" opacity=\"0\">\n" +
                    "\t  <animate id=\"animation2\" attributeName=\"opacity\" values=\"0;1\" dur=\"0.4s\" fill=\"freeze\" begin=\"animation1.end\"></animate>\n" +
                    "\t</rect>\n" +
                    "\t<rect width=\"30\" height=\"60\" fill=\"#fffa78\" stroke-width=\"2\" stroke=\"#111111\" x=\"40\" y=\"35\" opacity=\"0\">\n" +
                    "\t  <animate id=\"animation3\" attributeName=\"opacity\" values=\"0;1\" dur=\"0.4s\" fill=\"freeze\" begin=\"animation2.end\"></animate>\n" +
                    "\t</rect>\n" +
                    "\t<rect width=\"90\" height=\"20\" fill=\"#3cfbff\" stroke-width=\"2\" stroke=\"#111111\" x=\"10\" y=\"95\" opacity=\"0\">\n" +
                    "\t  <animate id=\"animation4\" attributeName=\"opacity\" values=\"0;1\" dur=\"0.4s\" fill=\"freeze\" begin=\"animation3.end\"></animate>\n" +
                    "\t</rect>\n" +
                    "\t<rect width=\"30\" height=\"40\" fill=\"#111111\" stroke-width=\"2\" stroke=\"#111111\" x=\"70\" y=\"15\" opacity=\"0\">\n" +
                    "\t  <animate id=\"animation5\" attributeName=\"opacity\" values=\"0;1\" dur=\"0.4s\" fill=\"freeze\" begin=\"animation4.end\"></animate>\n" +
                    "\t</rect>\n" +
                    "\t<rect width=\"30\" height=\"40\" fill=\"#ff5675\" stroke-width=\"2\" stroke=\"#111111\" x=\"130\" y=\"55\" opacity=\"0\">\n" +
                    "\t  <animate id=\"animation6\" attributeName=\"opacity\" values=\"0;1\" dur=\"0.4\" fill=\"freeze\" begin=\"animation5.end\"></animate>\n" +
                    "\t</rect>\n" +
                    "\t<rect width=\"30\" height=\"20\" fill=\"#c4e693\" stroke-width=\"2\" stroke=\"#111111\" x=\"100\" y=\"95\" opacity=\"0\">\n" +
                    "\t  <animate id=\"animation7\" attributeName=\"opacity\" values=\"0;1\" dur=\"0.4s\" fill=\"freeze\" begin=\"animation6.end\"></animate>\n" +
                    "\t</rect>\n" +
                    "\t<rect width=\"30\" height=\"20\" fill=\"none\" stroke-width=\"2\" stroke=\"#111111\" x=\"40\" y=\"15\" opacity=\"0\">\n" +
                    "\t  <animate id=\"animation8\" attributeName=\"opacity\" values=\"0;1\" dur=\"0.5s\" fill=\"freeze\" begin=\"animation7.end\"></animate>\n" +
                    "\t</rect>\n" +
                    "\t<rect width=\"30\" height=\"40\" fill=\"none\" stroke-width=\"2\" stroke=\"#111111\" x=\"10\" y=\"55\" opacity=\"0\">\n" +
                    "\t  <animate id=\"animation9\" attributeName=\"opacity\" values=\"0;1\" dur=\"0.5s\" fill=\"freeze\" begin=\"animation7.end\"></animate>\n" +
                    "\t</rect>\n" +
                    "\t<rect width=\"60\" height=\"40\" fill=\"none\" stroke-width=\"2\" stroke=\"#111111\" x=\"70\" y=\"55\" opacity=\"0\">\n" +
                    "\t  <animate id=\"animation10\" attributeName=\"opacity\" values=\"0;1\" dur=\"0.5s\" fill=\"freeze\" begin=\"animation7.end\"></animate>\n" +
                    "\t</rect>\n" +
                    "\t<rect width=\"30\" height=\"20\" fill=\"none\" stroke-width=\"2\" stroke=\"#111111\" x=\"130\" y=\"95\" opacity=\"0\">\n" +
                    "\t  <animate id=\"animation11\" attributeName=\"opacity\" values=\"0;1\" dur=\"0.5s\" fill=\"freeze\" begin=\"animation7.end\"></animate>\n" +
                    "\t</rect>\n" +
                    "</g>\n" +
                    "<text font-size=\"20\" x=\"210\" y=\"40\" opacity=\"0\">\n" +
                    "\t<tspan id=\"name\" dy=\"26\" x=\"210.0109466053608\">"+name+"</tspan>\n" +
                    "  <animate id=\"animation14\" attributeName=\"opacity\" values=\"0;1\" dur=\"0.5s\" fill=\"freeze\" begin=\"animation12.end\"></animate>\n" +
                    "</text>\n" +
                    "<text font-size=\"20\" x=\"210\" y=\"60\" opacity=\"0\">\n" +
                    "\t<tspan id=\"handle\" dy=\"26\" x=\"210.0109466053608\">"+handle+"</tspan>\n" +
                    "  <animate id=\"animation13\" attributeName=\"opacity\" values=\"0;1\" dur=\"0.5s\" fill=\"freeze\" begin=\"animation12.end\"></animate>\n" +
                    "</text>\n" +
                    "<text font-size=\"20\" x=\"160\" y=\"5\" opacity=\"0\">\n" +
                    "\t<tspan dy=\"26\" x=\"210.921875\">ALPHABET</tspan>\n" +
                    "  <animate id=\"animation12\" attributeName=\"opacity\" values=\"0;1\" dur=\"0.5s\" fill=\"freeze\" begin=\"animation7.end\"></animate>\n" +
                    "</text>\n" +
                    "<rect width=\"350\" height=\"131\" fill=\"none\" stroke=\"#111111\" rx=\"20\" ry=\"20\" stroke-width=\"3\" x=\"1\" y=\"1\"></rect>\n" +
                    "</svg>", HttpStatus.OK);
        }

        @RequestMapping(value = "/api/getCF", method = RequestMethod.GET, produces = "image/svg+xml", params = "handle")
        @ResponseBody
        public ResponseEntity<String> getCF(@RequestParam("handle") String handle) {
            String data = "";
            String preSvg = "<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" xmlns:svgjs=\"http://svgjs.com/svgjs\" width=\"353\" height=\"134\">\n" +
                    "<defs>\n" +
                    "    <linearGradient id=\"grad1\" x1=\"0%\" y1=\"0%\" x2=\"30%\" y2=\"0%\">\n" +
                    "      <stop offset=\"0%\" style=\"stop-color:rgb(0,0,0);stop-opacity:1\" />\n" +
                    "      <stop offset=\"100%\" style=\"stop-color:rgb(255,0,0);stop-opacity:1\" />\n" +
                    "    </linearGradient>\n" +
                    "  </defs>" +
                    "<g>\n" +
                    "\t<rect width=\"30\" height=\"40\" fill=\"#c4e693\" stroke-width=\"2\" stroke=\"#111111\" x=\"10\" y=\"15\"></rect>\n" +
                    "\t<rect width=\"60\" height=\"40\" fill=\"#ffc519\" stroke-width=\"2\" stroke=\"#111111\" x=\"100\" y=\"15\"></rect>\n" +
                    "\t<rect width=\"30\" height=\"60\" fill=\"#fffa78\" stroke-width=\"2\" stroke=\"#111111\" x=\"40\" y=\"35\"></rect>\n" +
                    "\t<rect width=\"90\" height=\"20\" fill=\"#3cfbff\" stroke-width=\"2\" stroke=\"#111111\" x=\"10\" y=\"95\"></rect>\n" +
                    "\t<rect width=\"30\" height=\"40\" fill=\"#111111\" stroke-width=\"2\" stroke=\"#111111\" x=\"70\" y=\"15\"></rect>\n" +
                    "\t<rect width=\"30\" height=\"40\" fill=\"#ff5675\" stroke-width=\"2\" stroke=\"#111111\" x=\"130\" y=\"55\"></rect>\n" +
                    "\t<rect width=\"30\" height=\"20\" fill=\"#c4e693\" stroke-width=\"2\" stroke=\"#111111\" x=\"100\" y=\"95\"></rect>\n" +
                    "\t<rect width=\"30\" height=\"20\" fill=\"none\" stroke-width=\"2\" stroke=\"#111111\" x=\"40\" y=\"15\"></rect>\n" +
                    "\t<rect width=\"30\" height=\"40\" fill=\"none\" stroke-width=\"2\" stroke=\"#111111\" x=\"10\" y=\"55\"></rect>\n" +
                    "\t<rect width=\"60\" height=\"40\" fill=\"none\" stroke-width=\"2\" stroke=\"#111111\" x=\"70\" y=\"55\"></rect>\n" +
                    "\t<rect width=\"30\" height=\"20\" fill=\"none\" stroke-width=\"2\" stroke=\"#111111\" x=\"130\" y=\"95\"></rect>\n" +
                    "</g>\n" +
                    "<rect id=\"colorR\" width=\"130\" height=\"40\" fill=\"";
            String middleSvg = "\" x=\"200\" y=\"55\" rx=\"10\" ry=\"10\"></rect>\n" +
                    "<text id=\"handle\" font-size=\"20\" x=\"210\" y=\"55\" fill=\"white\">\n" +
                    "\t<tspan dy=\"26\" x=\"210.0109466053608\">";
            String postSvg = "</tspan>\n" +
                    "</text>\n" +
                    "<text font-size=\"20\" x=\"160\" y=\"5\">\n" +
                    "\t<tspan dy=\"26\" x=\"210.921875\">ALPHABET</tspan>\n" +
                    "</text>\n" +
                    "<rect width=\"350\" height=\"131\" fill=\"none\" stroke=\"#111111\" rx=\"20\" ry=\"20\" stroke-width=\"3\" x=\"1\" y=\"1\"></rect>\n" +
                    "</svg>";
            String color = "blue";
            ArrayList<String> colList = new ArrayList<String>(Arrays.asList("grey", "green", "cyan", "blue", "violet", "orange", "red", "url(#grad1)"));
            String baseUrl = "https://codeforces.com/api/user.info?handles=";
            String url=baseUrl+handle;
            DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(url);
            WebClient client = WebClient.builder().uriBuilderFactory(factory).build();
            Object trg = client.get().retrieve().bodyToMono(Object.class).block();
            data = trg.toString();
            data = data.replaceAll(" ","");
            StringTokenizer tokens = new StringTokenizer(data, "{}[]=\",");
            ArrayList<String> strArr = new ArrayList<String>();
            Integer rating = null;
            while(tokens.hasMoreTokens()) {
                String tmp = tokens.nextToken();
                strArr.add(tmp);
            }
            rating= Integer.parseInt(strArr.get(8));

            if (rating >= 3000) {
                color = colList.get(7).toString();
            }
            else if (rating >= 2400) {
                color = colList.get(6).toString();
            }
            else if (rating >= 2100) {
                color = colList.get(5).toString();
            }
            else if (rating >= 1900) {
                color = colList.get(4).toString();
            }
            else if (rating >= 1600) {
                color = colList.get(3).toString();
            }
            else if (rating >= 1400) {
                color = colList.get(2).toString();
            }
            else if (rating >= 1200) {
                color = colList.get(1).toString();
            }
            else {
                color = colList.get(0).toString();
            }

            return new ResponseEntity<String>(preSvg+color+middleSvg+handle+postSvg, HttpStatus.OK);
        }
    }


}
