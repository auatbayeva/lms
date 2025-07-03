package kz.bitlab.lms.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class CourseDto {
    private Long id;
    private String name;
    private String description;
//    private LocalDateTime createdTime;
//    private LocalDateTime updatedTime;

}
