package uhs.alphabet.domain.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uhs.alphabet.domain.dto.BoardDto;
import uhs.alphabet.domain.entity.BoardEntity;
import uhs.alphabet.domain.repository.BoardRepository;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public List<BoardDto> getBoardList() {
        List<BoardEntity> boardEntities = boardRepository.findAll();
        List<BoardDto> boardDtos = new ArrayList<>();
        for (BoardEntity boardEntity : boardEntities) {
            BoardDto boardDto = BoardDto.builder()
                    .board_id(boardEntity.getBoard_id())
                    .title(boardEntity.getTitle())
                    .content(boardEntity.getContent())
                    .pw(boardEntity.getPw())
                    .count(boardEntity.getCount())
                    .created_time(boardEntity.getCreated_time())
                    .modified_time(boardEntity.getModified_time())
                    .build();
            boardDtos.add(boardDto);
        }

        return boardDtos;
    }

    @Transactional
    public Long saveBoard(BoardDto boardDto) {
        return boardRepository.save(boardDto.toEntity()).getBoard_id();
    }

    @Transactional
    public BoardDto getBoard(Long id) {
        Optional<BoardEntity> boardEntityWrapper = boardRepository.findById(id);
        BoardEntity boardEntity = boardEntityWrapper.get();
        BoardDto boardDto = BoardDto.builder()
                .board_id(boardEntity.getBoard_id())
                .title(boardEntity.getTitle())
                .content(boardEntity.getContent())
                .pw(boardEntity.getPw())
                .count(boardEntity.getCount())
                .created_time(boardEntity.getCreated_time())
                .modified_time(boardEntity.getModified_time())
                .build();

        return boardDto;
    }

    @Transactional
    public void deletePost(Long id, String pw) {
        Optional<BoardEntity> boardEntityOptional = boardRepository.findById(id);
        BoardEntity boardEntity = boardEntityOptional.get();
        if (boardEntity.getPw().toString().equals(pw)) boardRepository.deleteById(id);
    }

}
