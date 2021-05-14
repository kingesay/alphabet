package uhs.alphabet.domain.dto;

import lombok.*;
import java.time.LocalDateTime;

import uhs.alphabet.domain.entity.BoardEntity;
import uhs.alphabet.domain.entity.PersonEntity;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDto {
    private Long board_id;
    @NotNull
    private String title;
    private String content;
    private String pw;
    private int count;
    private LocalDateTime created_time;
    private LocalDateTime modified_time;

    public BoardEntity toEntity() {
        BoardEntity boardEntity = BoardEntity.builder()
                .board_id(board_id)
                .title(title)
                .content(content)
                .pw(pw)
                .count(count)
                .build();

        return boardEntity;
    }

    @Builder
    public BoardDto(Long board_id, String title, String content, String pw, int count, LocalDateTime created_time, LocalDateTime modified_time) {
        this.board_id = board_id;
        this.title = title;
        this.content = content;
        this.pw = pw;
        this.count = count;
        this.created_time = created_time;
        this.modified_time = modified_time;
    }
}
