package uhs.alphabet.domain.repository;

import org.springframework.stereotype.Repository;
import uhs.alphabet.domain.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Long>{

}
