package uhs.alphabet.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "person")
public class PersonEntity extends TimeEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false, name = "handle")
    private String handle;

    @Column(length = 50, nullable = false, name = "stunum")
    private String stunum;

    @Column(length = 50, nullable = false, name = "rating")
    private int rating;

    @Column(length = 50, name = "name")
    private String name;

    @Builder
    public PersonEntity(Long id, String handle, String stunum, int rating, String name) {
        this.id = id;
        this.handle = handle;
        this.stunum = stunum;
        this.rating = rating;
        this.name = name;
    }


}
