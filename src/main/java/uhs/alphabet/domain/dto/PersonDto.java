package uhs.alphabet.domain.dto;

import lombok.*;
import java.time.LocalDateTime;
import uhs.alphabet.domain.entity.PersonEntity;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PersonDto {
    private Long id;
    private String handle;
    private String stunum;
    private int rating;
    private String name;
    private LocalDateTime created_time;
    private LocalDateTime modified_time;

    public PersonEntity toEntity() {
        PersonEntity personEntity = PersonEntity.builder()
                .id(id)
                .handle(handle)
                .stunum(stunum)
                .rating(rating)
                .name(name)
                .build();
        return personEntity;
    }

    @Builder
    public PersonDto(Long id, String handle, String stunum, int rating, String name) {
        this.id = id;
        this.handle = handle;
        this.stunum = stunum;
        this.rating = rating;
        this.name = name;
    }

}
