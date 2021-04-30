package uhs.alphabet.domain.service;

import uhs.alphabet.domain.dto.PersonDto;
import uhs.alphabet.domain.entity.PersonEntity;
import uhs.alphabet.domain.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class PersonService {
    private PersonRepository personRepository;

    @Transactional
    public PersonDto getPerson() {
        List<PersonEntity> personEntities = personRepository.findAll();
        PersonEntity personEntity = personEntities.get(0);
        PersonDto personDto = PersonDto.builder()
                .id(personEntity.getId())
                .handle(personEntity.getHandle())
                .stunum(personEntity.getStunum())
                .rating(personEntity.getRating())
                .name(personEntity.getName())
                .build();


        return personDto;
    }

    @Transactional
    public Long savePerson(PersonDto personDto) {
        return personRepository.save(personDto.toEntity()).getId();
    }
}
