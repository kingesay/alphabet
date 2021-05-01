package uhs.alphabet.domain.test;

import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import uhs.alphabet.domain.entity.PersonEntity;
import uhs.alphabet.domain.repository.PersonRepository;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class saveRepositoryTest {
    @Autowired
    PersonRepository personRepository;

    @After
    public void cleanup() {
        personRepository.deleteAll();
    }

    @Test
    public void save_person() {
        personRepository.save(PersonEntity.builder()
                .handle("test")
                .stunum("1234")
                .rating(1234)
                .name("asd")
                .build()
        );

        List<PersonEntity> personList = personRepository.findAll();

        PersonEntity personEntity = personList.get(0);
        assertThat(personEntity.getName()).isEqualTo("asd");
    }

    @Test
    public void get_pefson() {

    }
}

