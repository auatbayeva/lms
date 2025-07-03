package kz.bitlab.lms.mapper;

import kz.bitlab.lms.dto.ChapterDto;
import kz.bitlab.lms.entity.Chapter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CourseMapper.class})
public interface ChapterMapper {

    @Mapping(source = "courseId", target = "course.id")
    Chapter toEntity(ChapterDto dto);

    @Mapping(source = "course.id", target = "courseId")
    ChapterDto toDto(Chapter chapter);
}
