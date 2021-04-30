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
    private int stunum;
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
    public PersonDto(Long id, String handle, int stunum, int rating, String name, LocalDateTime created_time, LocalDateTime modified_time) {
        this.id = id;
        this.handle = handle;
        this.stunum = stunum;
        this.rating = rating;
        this.name = name;
        this.created_time = created_time;
        this.modified_time = modified_time;
    }

    public PersonDto(PersonEntity personEntity) {
        this.id = personEntity.getId();
        this.handle = personEntity.getHandle();
        this.stunum = personEntity.getStunum();
        this.rating = personEntity.getRating();
        this.name = personEntity.getName();
        this.created_time = personEntity.getCreated_time();
        this.modified_time = personEntity.getModified_time();
    }
}
