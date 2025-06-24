package kz.bitlab.lms.service;

import kz.bitlab.lms.dto.LessonDto;
import kz.bitlab.lms.entity.Lesson;
import kz.bitlab.lms.mapper.LessonMapper;
import kz.bitlab.lms.repository.LessonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LessonService {

    private final LessonRepository repository;
    private final LessonMapper mapper;

    public LessonService(LessonRepository repository, LessonMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public List<LessonDto> getAllLessons() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public LessonDto createLesson(LessonDto dto) {
        Lesson lesson = mapper.toEntity(dto);
        Lesson saved = repository.save(lesson);
        return mapper.toDto(saved);
    }
}