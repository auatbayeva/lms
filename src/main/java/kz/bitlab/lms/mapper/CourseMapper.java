package kz.bitlab.lms.mapper;

import kz.bitlab.lms.dto.CourseDto;
import kz.bitlab.lms.entity.Course;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    CourseDto toDto(Course course);
    Course toEntity(CourseDto dto);
}
