package uhs.alphabet.domain.entity;

import org.junit.After;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import uhs.alphabet.domain.dto.BoardDto;
import uhs.alphabet.domain.entity.BoardEntity;
import uhs.alphabet.domain.entity.PersonEntity;
import uhs.alphabet.domain.repository.BoardRepository;
import uhs.alphabet.domain.repository.PersonRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class EntityTest {

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private PersonRepository personRepository;

    @After
    public void cleanup() {
        personRepository.deleteAll();
        boardRepository.deleteAll();
    }
    @BeforeEach
    public void cleanupEach() {
        personRepository.deleteAll();
        boardRepository.deleteAll();
    }

    @Test
    public void saveBoard() {
        BoardEntity boardEntity = BoardEntity.builder()
                .title("saveTestTitle")
                .content("saveTestContent")
                .pw("1234")
                .writer("writer")
                .ip("ip")
                .visible(true)
                .build();
        boardRepository.save(BoardEntity.builder()
                .title("saveTestTitle")
                .content("saveTestContent")
                .pw("1234")
                .visible(true)
                .writer("writer")
                .ip("ip")
                .build()
        );
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        List<BoardEntity> boardEntityWrapper = boardRepository.findByTitleContaining("saveTestTitle");
        BoardEntity boardEntityTest = boardEntityWrapper.get(0);
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
    public void deleteBoard() {
        boardRepository.save(BoardEntity.builder()
                .title("deleteTestTitle1")
                .content("deleteTestContent1")
                .pw("1234")
                .visible(true)
                .writer("writer1")
                .ip("ip1")
                .build()
        );
        boardRepository.save(BoardEntity.builder()
                .title("deleteTestTitle2")
                .content("deleteTestContent2")
                .pw("1234")
                .visible(true)
                .writer("writer2")
                .ip("ip2")
                .build()
        );
        List<BoardEntity> boardEntities = boardRepository.findByTitleContaining("deleteTest");
        int sz = boardEntities.size();
        Assertions.assertEquals(sz, 2);
        BoardEntity boardEntity1 = boardEntities.get(0);
        BoardEntity boardEntity2 = boardEntities.get(1);

        boardRepository.deleteById(boardEntity1.getBoard_id());

        boardEntities = boardRepository.findByTitleContaining("deleteTest");
        sz = boardEntities.size();
        Assertions.assertEquals(sz, 1);
    }

    @Test
    public void savePerson() {
        PersonEntity personEntity = PersonEntity.builder()
                .handle("savePersonHandle")
                .stunum("savePerson1234")
                .id(1L)
                .name("name")
                .rating(1700)
                .build();
        personRepository.save(PersonEntity.builder()
                .handle("savePersonHandle")
                .stunum("savePerson1234")
                .rating(1700)
                .name("name")
                .build()
        );
        List<PersonEntity> personEntityWrapper = personRepository.findByStunumContaining("savePerson1234");
        PersonEntity personEntityTest = personEntityWrapper.get(0);
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        Assertions.assertEquals(personEntity.getName(), personEntityTest.getName());
        Assertions.assertEquals(personEntity.getHandle(), personEntityTest.getHandle());
        Assertions.assertEquals(personEntity.getStunum(), personEntityTest.getStunum());
        Assertions.assertEquals(personEntity.getRating(), personEntityTest.getRating());
        Assertions.assertEquals(now.format(formatter), personEntityTest.getCreatedTime().format(formatter));
        Assertions.assertEquals(now.format(formatter), personEntityTest.getModified_time().format(formatter));
    }

    @Test
    public void deletePerson() {
        personRepository.save(PersonEntity.builder()
                .handle("handle")
                .stunum("1234")
                .rating(1700)
                .name("name")
                .build()
        );
        personRepository.save(PersonEntity.builder()
                .handle("handle")
                .stunum("1234")
                .rating(1700)
                .name("name")
                .build()
        );
        List<PersonEntity> personEntities = personRepository.findByStunumContaining("1234");
        int sz = personEntities.size();
        Assertions.assertEquals(sz, 2);
        PersonEntity personEntity1 = personEntities.get(0);
        PersonEntity personEntity2 = personEntities.get(1);

        personRepository.deleteById(personEntity1.getId());

        personEntities = personRepository.findByStunumContaining("1234");
        sz = personEntities.size();
        Assertions.assertEquals(sz, 1);
    }
}
