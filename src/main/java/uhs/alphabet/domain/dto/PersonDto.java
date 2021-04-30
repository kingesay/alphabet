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
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

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
    public PersonDto(Long id, String handle, int stunum, int rating, String name, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.handle = handle;
        this.stunum = stunum;
        this.rating = rating;
        this.name = name;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
