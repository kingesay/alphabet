package uhs.alphabet.domain.repository;

import uhs.alphabet.domain.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<PersonEntity, Long>{

}
