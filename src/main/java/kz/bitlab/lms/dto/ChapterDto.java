package kz.bitlab.lms.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ChapterDto {
    private Long id;
    private String name;
    private String description;
    private int order;
    private Long courseId;

}
