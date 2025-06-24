package kz.bitlab.lms.mapper;

import kz.bitlab.lms.dto.LessonDto;
import kz.bitlab.lms.entity.Lesson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ChapterMapper.class})
public interface LessonMapper {

    @Mapping(source = "chapter.name",target = "chapterName")
    LessonDto toDto(Lesson lesson);
    Lesson toEntity(LessonDto dto);

}
