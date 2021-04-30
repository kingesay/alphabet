package uhs.alphabet.web;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.reactive.function.client.WebClient;

import uhs.alphabet.domain.dto.PersonDto;
import uhs.alphabet.domain.service.PersonService;

import java.io.*;
import java.util.*;


@Controller
public class IndexController {

    private PersonService personService;

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

    @GetMapping("/testPerson")
    public String test(Model model) {
        PersonDto personDto;
        try {
            personDto = personService.getPerson();
        } catch (Exception e) {
            model.addAttribute("person", e);
            return "test";
        }
        model.addAttribute("person", personDto);
        return "test";
    }

    @RestController
    public class apiControl {
        @RequestMapping(value = "/api/getSVG", method = RequestMethod.GET, produces = "image/svg+xml", params = "stuID")
        @ResponseBody
        public ResponseEntity<String> getSVG(@RequestParam("stuID") Integer stuID) {
            BufferedReader br = null;
            HashMap<Integer, String> mem = new HashMap<Integer, String>();
            try {
//                TODO: 파일 위치 변수에 넣기
                br = new BufferedReader(new FileReader("/home/ec2-user/app/step2/stuList/in.txt"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            while (true) {
                String Line = null;
                try {
                    Line = br.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (Line==null) break;
                StringTokenizer str = new StringTokenizer(Line," ");
                String stuNum =  str.nextToken();
                String handle = str.nextToken();
                mem.put(Integer.parseInt(stuNum), handle);
            }

            String handle = "None";
            if (mem.containsKey(stuID)) {
                handle = mem.get(stuID);
            }
            return new ResponseEntity<String>("<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" xmlns:svgjs=\"http://svgjs.com/svgjs\" width=\"353\" height=\"134\"><g><rect width=\"30\" height=\"40\" fill=\"#c4e693\" stroke-width=\"2\" stroke=\"#111111\" x=\"10\" y=\"15\"></rect><rect width=\"60\" height=\"40\" fill=\"#ffc519\" stroke-width=\"2\" stroke=\"#111111\" x=\"100\" y=\"15\"></rect><rect width=\"30\" height=\"60\" fill=\"#fffa78\" stroke-width=\"2\" stroke=\"#111111\" x=\"40\" y=\"35\"></rect><rect width=\"90\" height=\"20\" fill=\"#3cfbff\" stroke-width=\"2\" stroke=\"#111111\" x=\"10\" y=\"95\"></rect><rect width=\"30\" height=\"40\" fill=\"#4b4b4b\" stroke-width=\"2\" stroke=\"#111111\" x=\"70\" y=\"15\"></rect><rect width=\"30\" height=\"40\" fill=\"#ff5675\" stroke-width=\"2\" stroke=\"#111111\" x=\"130\" y=\"55\"></rect><rect width=\"30\" height=\"20\" fill=\"#c4e693\" stroke-width=\"2\" stroke=\"#111111\" x=\"100\" y=\"95\"></rect><rect width=\"30\" height=\"20\" fill=\"none\" stroke-width=\"2\" stroke=\"#111111\" x=\"40\" y=\"15\"></rect><rect width=\"30\" height=\"40\" fill=\"none\" stroke-width=\"2\" stroke=\"#111111\" x=\"10\" y=\"55\"></rect><rect width=\"60\" height=\"40\" fill=\"none\" stroke-width=\"2\" stroke=\"#111111\" x=\"70\" y=\"55\"></rect><rect width=\"30\" height=\"20\" fill=\"none\" stroke-width=\"2\" stroke=\"#111111\" x=\"130\" y=\"95\"></rect></g><text font-size=\"20\" id=\"handle\" x=\"210\" y=\"55.828125\"><tspan dy=\"26\" x=\"210\">"+handle+"</tspan></text><text font-size=\"20\" x=\"210.921875\" y=\"5.828125\"><tspan dy=\"26\" x=\"210.921875\">ALPHABET</tspan></text><rect width=\"350\" height=\"131\" fill=\"none\" stroke=\"#111111\" rx=\"20\" ry=\"20\" stroke-width=\"3\" x=\"1\" y=\"1\"></rect></svg>", HttpStatus.OK);
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
