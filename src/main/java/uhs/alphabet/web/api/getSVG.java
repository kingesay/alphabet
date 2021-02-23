package uhs.alphabet.web.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.util.ArrayList;
import java.util.StringTokenizer;

@Getter
public class getSVG {

    private String url;

    public getSVG(String stuID) {

    }
}
