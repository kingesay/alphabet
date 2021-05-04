package uhs.alphabet.domain.repository;

import uhs.alphabet.domain.dto.BoardDto;
import uhs.alphabet.domain.entity.BoardEntity;
import uhs.alphabet.domain.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

}
