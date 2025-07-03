package kz.bitlab.lms.mapper;

import kz.bitlab.lms.dto.LessonDto;
import kz.bitlab.lms.entity.Lesson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ChapterMapper.class})
public interface LessonMapper {

    @Mapping(source = "chapter.id", target = "chapterId")
    LessonDto toDto(Lesson lesson);

    @Mapping(source = "chapterId", target = "chapter.id")
    Lesson toEntity(LessonDto dto);

}
