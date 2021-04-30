package uhs.alphabet.domain.service;

import uhs.alphabet.domain.dto.PersonDto;
import uhs.alphabet.domain.entity.PersonEntity;
import uhs.alphabet.domain.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@AllArgsConstructor
@Service
public class PersonService {
    private PersonRepository personRepository;

    @Transactional
    public PersonDto getPerson() {
        List<PersonEntity> personEntities = personRepository.findAll();
//        if (personEntities.isEmpty()) return PersonDto.builder()
//                .id(1111L)
//                .handle("null")
//                .stunum(-1)
//                .rating(-1)
//                .name("null")
//                .build();
        PersonEntity personEntity = personEntities.get(0);
        PersonDto personDto = PersonDto.builder()
                .id(personEntity.getId())
                .handle(personEntity.getHandle())
                .stunum(personEntity.getStunum())
                .rating(personEntity.getRating())
                .name(personEntity.getName())
                .created_time(personEntity.getCreated_time())
                .modified_time(personEntity.getModified_time())
                .build();

        return personDto;
    }

    @Transactional
    public Long savePerson(PersonDto personDto) {
        return personRepository.save(personDto.toEntity()).getId();
    }
}
