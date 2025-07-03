package kz.bitlab.lms.service;

import kz.bitlab.lms.dto.LessonDto;
import kz.bitlab.lms.entity.Lesson;
import kz.bitlab.lms.exception.LessonNotFoundException;
import kz.bitlab.lms.mapper.LessonMapper;
import kz.bitlab.lms.repository.LessonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LessonService {

    private final LessonRepository repository;
    private final LessonMapper mapper;
    private static final Logger log = LoggerFactory.getLogger(LessonService.class);

    public LessonService(LessonRepository repository, LessonMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<LessonDto> getAllLessons() {
        List<LessonDto> lessons = repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        log.info("Retrieved {} lesson(s)", lessons.size());
        return lessons;
    }

    public LessonDto createLesson(LessonDto dto) {
        log.info("Creating new lesson: {}", dto.getName());
        Lesson lesson = mapper.toEntity(dto);
        Lesson saved = repository.save(lesson);
        log.debug("Lesson created with ID: {}", saved.getId());
        return mapper.toDto(saved);
    }

    public Lesson getLessonById(Long id) {
        log.info("Fetching lesson by ID: {}", id);
        return repository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Lesson with ID {} not found", id);
                    return new LessonNotFoundException("Lesson with ID " + id + " not found");
                });
    }

    public LessonDto updateLesson(Long id, LessonDto dto) {
        log.info("Updating lesson with ID: {}", id);
        Lesson lesson = getLessonById(id);
        lesson.setName(dto.getName());
        lesson.setContent(dto.getContent());
        Lesson updated = repository.save(lesson);
        log.info("Lesson updated successfully with ID: {}", updated.getId());
        return mapper.toDto(updated);
    }

    public void deleteLesson(Long id) {
        log.info("Deleting lesson with ID: {}", id);
        Lesson lesson = getLessonById(id);
        repository.delete(lesson);
        log.info("Lesson with ID {} deleted successfully", id);
    }

    public LessonDto getLessonDtoById(Long id) {
        return mapper.toDto(getLessonById(id));
    }
}
