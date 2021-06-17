package uhs.alphabet.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import uhs.alphabet.domain.dto.BoardDto;
import uhs.alphabet.domain.service.BoardService;
import uhs.alphabet.domain.service.PersonService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
@Import({PersonService.class, BoardService.class})
@MockBean(JpaMetamodelMappingContext.class)
public class IndexControllerMockTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PersonService personService;
    @MockBean
    private BoardService boardService;

    private LocalDateTime now = LocalDateTime.now();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private String cur = now.format(formatter);

    @Test
    public void getTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk());
    }
    @Test
    public void bobTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/bob"))
                .andExpect(status().isOk());
    }
    @Test
    public void introductionTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/introduction"))
                .andExpect(status().isOk());
    }

    @Test
    public void historyTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/history"))
                .andExpect(status().isOk());
    }
    @Test
    public void contactTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/contact"))
                .andExpect(status().isOk());
    }
    @Test
    public void mirrorTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/mirror"))
                .andExpect(status().isOk());
    }
    @Test
    public void journalTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/journal"))
                .andExpect(status().isOk());
    }
    @Test
    public void postTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/post"))
                .andExpect(status().isOk());
    }
    @Test
    public void howtouseTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/howtouse"))
                .andExpect(status().isOk());
    }
    @Test
    public void putPostTest() throws Exception {
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

        mockMvc.perform(MockMvcRequestBuilders.post("/post"))
                .andExpect(status().is3xxRedirection());
        // todo: param에 boardDto 넘겨주는 방법 찾아보기
//        param으로 여러개 넘겨주면 해결 할 수 있다
//        todo: 수정 날짜 관련해서 버그 있는듯 파라미터로 수정시간 넘겨주면 에러나네

        Mockito.when(boardService.saveBoard(boardDto)).thenReturn(1L);
        mockMvc.perform(MockMvcRequestBuilders.post("/post")
                .param("board_id", "1")
                .param("title", "test")
                .param("content", "test")
                .param("writer", "test")
                .param("visible", "true")
                .param("pw", "1234")
                .param("created_time", cur)
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/board"));
        mockMvc.perform(MockMvcRequestBuilders.post("/post")
                .param("board_id", "1")
                .param("title", "test")
                .param("content", "test")
                .param("writer", "admin")
                .param("visible", "true")
                .param("pw", "1234")
                .param("created_time", cur)
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/board"));
    }

    @Test
    public void searchTest() throws Exception {
        String arg = "test";
        List<BoardDto> boardDtos = new ArrayList<>();
        BoardDto boardDto = BoardDto.builder()
                .board_id(1L)
                .title("test")
                .content("test")
                .writer("test")
                .visible(true)
                .pw("1234")
                .created_time(cur)
                .count(1)
                .build();
        boardDtos.add(boardDto);
        Mockito.when(boardService.searchPosts(arg)).thenReturn(boardDtos);
        mockMvc.perform(MockMvcRequestBuilders.get("/board/search")
                .param("keyword", arg)
                .param("model", "")
        )
                .andExpect(status().isOk());
//                .andExpect(model().attribute("boardList", boardDtos.toString()));
    }
    @Test
    public void detailTest() throws Exception {
        Long no = 1L;
        BoardDto boardDto = BoardDto.builder()
                .board_id(1L)
                .title("test")
                .content("test")
                .writer("test")
                .visible(true)
                .pw("1234")
                .created_time(cur)
                .count(1)
                .build();
        Mockito.when(boardService.getBoard(no)).thenReturn(boardDto);
        mockMvc.perform(MockMvcRequestBuilders.get("/board/{no}", no)
                .param("model", "")
        )
//                .andExpect(model().attribute("board", "asd"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("detail isVisible Test")
    public void detaileTest2() throws Exception {
        Long no = 1L;
        BoardDto boardDto = BoardDto.builder()
                .board_id(no)
                .title("test")
                .content("test")
                .writer("test")
                .visible(false)
                .pw("1234")
                .created_time(cur)
                .count(1)
                .build();
        Mockito.when(boardService.getBoard(no)).thenReturn(boardDto);
        mockMvc.perform(MockMvcRequestBuilders.get("/board/{no}", no)
                .param("model", "")
        )
                .andExpect(status().isOk());
    }
    @Test
    @DisplayName("delete test")
    public void DeleteMapingPostTest2() throws Exception {
        Long no = 1L;
        String pw = "1234";

        BoardDto boardDto = BoardDto.builder()
                .board_id(no)
                .title("test")
                .content("test")
                .writer("test")
                .visible(false)
                .pw(pw)
                .created_time(cur)
                .count(1)
                .build();
        mockMvc.perform(MockMvcRequestBuilders.delete("/post/{no}", no)
                .param("pw", pw)
        )
                .andExpect(header().string("Location", "/board"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @DisplayName("dodeleteTest")
    public void dodeleteTest() throws Exception {
        Long no = 1L;
        String pw = "1234";

        mockMvc.perform(MockMvcRequestBuilders.get("/dodelete/{no}", no)
                .param("model", "")
        )
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("doeditTest")
    public void doeditTest() throws Exception {
        Long no = 1L;
        String pw = "1234";

        mockMvc.perform(MockMvcRequestBuilders.get("/doedit/{no}", no)
                .param("model", "")
        )
                .andExpect(status().isOk());
    }

//    todo: 수정 시간과 생성 시간 데이터 타입이 다르다
    @Test
    public void listTest() throws Exception {
        int pageNum = 1;
        List<BoardDto> boardDtos = new ArrayList<>();
        ArrayList<Integer> pageList = new ArrayList<>();
        now = LocalDateTime.now();
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
        for (int i = 0; i < 4; i++) {
            boardDtos.add(boardDto);
        }
        pageList.add(1);
        Mockito.when(boardService.getBoardList(pageNum)).thenReturn(boardDtos);
        Mockito.when(boardService.getPageList(pageNum)).thenReturn(pageList);
        mockMvc.perform(MockMvcRequestBuilders.get("/board"))
                .andExpect(status().isOk());
    }

    @Test
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
        Mockito.when(boardService.saveBoard(boardDto)).thenReturn(1L);
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
    }
}

