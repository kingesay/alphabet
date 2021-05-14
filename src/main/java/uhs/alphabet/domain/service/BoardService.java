package uhs.alphabet.domain.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
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
    private static final int BLOCK_PAGE_NUM_COUNT = 5;  // 블럭에 존재하는 페이지 번호 수
    private static final int PAGE_POST_COUNT = 4;       // 한 페이지에 존재하는 게시글 수

    private BoardDto convertEntityToDto(BoardEntity boardEntity) {
        return BoardDto.builder()
                .board_id(boardEntity.getBoard_id())
                .title(boardEntity.getTitle())
                .content(boardEntity.getContent())
                .pw(boardEntity.getPw())
                .count(boardEntity.getCount())
                .created_time(boardEntity.getCreatedTime())
                .modified_time(boardEntity.getModified_time())
                .build();
    }

    @Transactional
    public List<BoardDto> getBoardList(Integer pageNum) {
        Page<BoardEntity> page = boardRepository.findAll(PageRequest.of(pageNum-1, PAGE_POST_COUNT, Sort.by(Sort.Direction.ASC, "createdTime")));
        List<BoardEntity> boardEntities = page.getContent();
        List<BoardDto> boardDtos = new ArrayList<>();
        if (boardEntities.isEmpty()) return boardDtos;
        for (BoardEntity boardEntity : boardEntities) {
            boardDtos.add(this.convertEntityToDto(boardEntity));
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
                .created_time(boardEntity.getCreatedTime())
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

    @Transactional
    public List<BoardDto> searchPosts(String keyword) {
        List<BoardEntity> boardEntities = boardRepository.findByTitleContaining(keyword);
        List<BoardDto> boardDtos = new ArrayList<>();

        if (boardEntities.isEmpty()) return boardDtos;

        for (BoardEntity boardEntity : boardEntities) {
            boardDtos.add(this.convertEntityToDto(boardEntity));
        }

        return boardDtos;
    }

    @Transactional
    public Long getBoardCount() {
        return boardRepository.count();
    }

    public ArrayList<Integer> getPageList(Integer curPageNum) {
        ArrayList<Integer> pageList = new ArrayList<Integer>();
        // 총 게시글 갯수
        Double postsTotalCount = Double.valueOf(this.getBoardCount());

        // 총 게시글 기준으로 계산한 마지막 페이지 번호 계산 (올림으로 계산)
        Integer totalLastPageNum = (int)(Math.ceil((postsTotalCount/PAGE_POST_COUNT)));

        // 현재 페이지를 기준으로 블럭의 마지막 페이지 번호 계산
        Integer blockLastPageNum = (totalLastPageNum > curPageNum + BLOCK_PAGE_NUM_COUNT)
                ? curPageNum + BLOCK_PAGE_NUM_COUNT
                : totalLastPageNum;

        // 페이지 시작 번호 조정
        curPageNum = (curPageNum <= 3) ? 1 : curPageNum - 2;

        // 페이지 번호 할당
        for (int val = curPageNum, idx = 0; val <= blockLastPageNum; val++, idx++) {
            if (idx>5) break;
            pageList.add(val);
        }

        return pageList;
    }

}
