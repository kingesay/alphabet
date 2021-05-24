package uhs.alphabet.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "board")
public class BoardEntity extends TimeEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long board_id;

    @Column(length = 30, nullable = false)
    private String title;

    @Column(length = 4000)
    private String content;

    @Column(length = 20, nullable = false)
    private String pw;

    @Column
    private int count;

    @Column
    private boolean visible;

    @Column
    private String ip;

    @Builder
    public BoardEntity(Long board_id, String title, String content, String pw, int count, String ip, boolean visible) {
        this.board_id = board_id;
        this.title = title;
        this.content = content;
        this.pw = pw;
        this.count = count;
        this.ip = ip;
        this.visible = visible;
    }


}
