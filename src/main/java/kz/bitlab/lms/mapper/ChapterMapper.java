package kz.bitlab.lms.mapper;

import kz.bitlab.lms.dto.ChapterDto;
import kz.bitlab.lms.entity.Chapter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CourseMapper.class})
public interface ChapterMapper {

    @Mapping(source = "course.name",target = "courseName")
    ChapterDto toDto(Chapter chapter);
    Chapter toEntity(ChapterDto dto);
}
