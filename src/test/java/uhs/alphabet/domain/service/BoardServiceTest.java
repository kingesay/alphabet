package uhs.alphabet.domain.service;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import uhs.alphabet.domain.entity.BoardEntity;
import uhs.alphabet.domain.entity.PersonEntity;
import uhs.alphabet.domain.repository.BoardRepository;
import uhs.alphabet.domain.repository.PersonRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BoardServiceTest {

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private PersonRepository personRepository;


    @After
    public void cleanup() {
        personRepository.deleteAll();
        boardRepository.deleteAll();
    }

    @Test
    public void saveBoard() {
        BoardEntity boardEntity = BoardEntity.builder()
                .title("title")
                .board_id(1L)
                .content("content")
                .pw("1234")
                .writer("writer")
                .ip("ip")
                .visible(true)
                .build();
        boardRepository.save(BoardEntity.builder()
                .title("title")
                .content("content")
                .pw("1234")
                .visible(true)
                .writer("writer")
                .ip("ip")
                .build()
        );
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Optional<BoardEntity> boardEntityWrapper = boardRepository.findById(1L);
        BoardEntity boardEntityTest = boardEntityWrapper.get();
        Assertions.assertEquals(boardEntity.getBoard_id(), boardEntityTest.getBoard_id());
        Assertions.assertEquals(boardEntity.getTitle(), boardEntityTest.getTitle());
        Assertions.assertEquals(boardEntity.getContent(), boardEntityTest.getContent());
        Assertions.assertEquals(boardEntity.getPw(), boardEntityTest.getPw());
        Assertions.assertEquals(boardEntity.getWriter(), boardEntityTest.getWriter());
        Assertions.assertEquals(boardEntity.getIp(), boardEntityTest.getIp());
        Assertions.assertEquals(boardEntity.isVisible(), boardEntityTest.isVisible());
        Assertions.assertEquals(now.format(formatter), boardEntityTest.getCreatedTime().format(formatter));
        Assertions.assertEquals(now.format(formatter), boardEntityTest.getModified_time().format(formatter));
    }

    @Test
    public void savePerson() {
        PersonEntity personEntity = PersonEntity.builder()
                .handle("handle")
                .stunum("1234")
                .id(1L)
                .name("name")
                .rating(1700)
                .build();
        personRepository.save(PersonEntity.builder()
                .handle("handle")
                .stunum("1234")
                .rating(1700)
                .name("name")
                .build()
        );
        Optional<PersonEntity> personEntityWrapper = personRepository.findById(1L);
        PersonEntity personEntityTest = personEntityWrapper.get();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        Assertions.assertEquals(personEntity.getName(), personEntityTest.getName());
        Assertions.assertEquals(personEntity.getHandle(), personEntityTest.getHandle());
        Assertions.assertEquals(personEntity.getStunum(), personEntityTest.getStunum());
        Assertions.assertEquals(personEntity.getId(), personEntityTest.getId());
        Assertions.assertEquals(personEntity.getRating(), personEntityTest.getRating());
        Assertions.assertEquals(now.format(formatter), personEntityTest.getCreatedTime().format(formatter));
        Assertions.assertEquals(now.format(formatter), personEntityTest.getModified_time().format(formatter));

    }
}
