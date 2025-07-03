package kz.bitlab.lms.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import kz.bitlab.lms.dto.LessonDto;
import kz.bitlab.lms.entity.Lesson;
import kz.bitlab.lms.mapper.LessonMapper;
import kz.bitlab.lms.repository.LessonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class LessonServiceTest {

    private LessonRepository lessonRepository;
    private LessonMapper lessonMapper;
    private LessonService lessonService;

    @BeforeEach
    public void setup() {
        lessonRepository = mock(LessonRepository.class);
        lessonMapper = mock(LessonMapper.class);
        lessonService = new LessonService(lessonRepository, lessonMapper);
    }

    @Test
    public void testGetLessonDtoById() {
        // given
        Lesson lesson = new Lesson();
        lesson.setId(1L);
        lesson.setName("Test Lesson");

        LessonDto lessonDto = new LessonDto();
        lessonDto.setId(1L);
        lessonDto.setName("Test Lesson");

        when(lessonRepository.findById(1L)).thenReturn(Optional.of(lesson));
        when(lessonMapper.toDto(lesson)).thenReturn(lessonDto);

        // when
        LessonDto result = lessonService.getLessonDtoById(1L);

        // then
        assertNotNull(result);
        assertEquals("Test Lesson", result.getName());
        verify(lessonRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreateLesson() {
        LessonDto dto = new LessonDto();
        dto.setName("New Lesson");

        Lesson entity = new Lesson();
        entity.setName("New Lesson");

        Lesson savedEntity = new Lesson();
        savedEntity.setId(5L);
        savedEntity.setName("New Lesson");

        LessonDto resultDto = new LessonDto();
        resultDto.setId(5L);
        resultDto.setName("New Lesson");

        when(lessonMapper.toEntity(dto)).thenReturn(entity);
        when(lessonRepository.save(entity)).thenReturn(savedEntity);
        when(lessonMapper.toDto(savedEntity)).thenReturn(resultDto);

        LessonDto result = lessonService.createLesson(dto);

        assertNotNull(result);
        assertEquals(5L, result.getId());
        assertEquals("New Lesson", result.getName());

        verify(lessonRepository, times(1)).save(entity);
    }

    @Test
    void testGetAllLessons() {
        Lesson lesson = new Lesson();
        lesson.setId(1L);
        lesson.setName("Test Lesson");

        LessonDto lessonDto = new LessonDto();
        lessonDto.setId(1L);
        lessonDto.setName("Test Lesson");

        when(lessonRepository.findAll()).thenReturn(List.of(lesson));
        when(lessonMapper.toDto(lesson)).thenReturn(lessonDto);

        List<LessonDto> result = lessonService.getAllLessons();

        assertEquals(1, result.size());
        assertEquals("Test Lesson", result.get(0).getName());
        verify(lessonRepository, times(1)).findAll();
    }

    @Test
    void testUpdateLesson() {
        Long id = 1L;

        Lesson existingLesson = new Lesson();
        existingLesson.setId(id);
        existingLesson.setName("Old Name");

        LessonDto updatedDto = new LessonDto();
        updatedDto.setName("Updated Name");

        Lesson savedLesson = new Lesson();
        savedLesson.setId(id);
        savedLesson.setName("Updated Name");

        LessonDto resultDto = new LessonDto();
        resultDto.setId(id);
        resultDto.setName("Updated Name");

        when(lessonRepository.findById(id)).thenReturn(Optional.of(existingLesson));
        when(lessonRepository.save(existingLesson)).thenReturn(savedLesson);
        when(lessonMapper.toDto(savedLesson)).thenReturn(resultDto);

        LessonDto result = lessonService.updateLesson(id, updatedDto);

        assertEquals("Updated Name", result.getName());
        verify(lessonRepository).save(existingLesson);
    }

    @Test
    void testDeleteLesson() {
        Long id = 1L;

        Lesson lesson = new Lesson();
        lesson.setId(id);

        when(lessonRepository.findById(id)).thenReturn(Optional.of(lesson));

        lessonService.deleteLesson(id);

        verify(lessonRepository, times(1)).delete(lesson);
    }

}
