package uhs.alphabet.web;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import uhs.alphabet.domain.dto.BoardDto;
import uhs.alphabet.domain.repository.PersonRepository;
import uhs.alphabet.domain.service.BoardService;
import uhs.alphabet.domain.service.PersonService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@AutoConfigureMockMvc
@Import({PersonService.class, BoardService.class})
public class IndexControllerTest {

    @Autowired
    private PersonService personService;
    @Autowired
    private BoardService boardService;
    @Autowired
    private MockMvc mockMvc;

    private LocalDateTime now = LocalDateTime.now();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private String cur = now.format(formatter);
//    @Test
    public void updateTest() throws Exception {
        Long no = 1L;
        BoardDto boardDto = BoardDto.builder()
                .board_id(1L)
                .title("test")
                .content("test")
                .writer("test")
                .visible(true)
                .pw("1234")
                .created_time(cur)
                .modified_time(now)
                .count(1)
                .build();
        now = LocalDateTime.now();
        cur = now.format(formatter);
        Long id = boardService.saveBoard(boardDto);
        mockMvc.perform(MockMvcRequestBuilders.put("/post/edit/{no}", no)
                .param("board_id", "1")
                .param("title", "test2")
                .param("content", "test")
                .param("writer", "admin")
                .param("visible", "true")
                .param("pw", "1234")
                .param("created_time", cur)
        )
                .andExpect(status().is3xxRedirection());
        BoardDto boardDtoTest = boardService.getBoard(1L);
        Assertions.assertEquals(1L, boardDtoTest.getBoard_id());
        Assertions.assertEquals("test2", boardDtoTest.getTitle());

    }
}
