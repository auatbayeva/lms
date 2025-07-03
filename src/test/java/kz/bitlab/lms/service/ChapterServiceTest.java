package kz.bitlab.lms.service;

import kz.bitlab.lms.dto.ChapterDto;
import kz.bitlab.lms.dto.CourseDto;
import kz.bitlab.lms.entity.Chapter;
import kz.bitlab.lms.entity.Course;
import kz.bitlab.lms.mapper.ChapterMapper;
import kz.bitlab.lms.mapper.CourseMapper;
import kz.bitlab.lms.repository.ChapterRepository;
import kz.bitlab.lms.repository.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ChapterServiceTest {

    private ChapterRepository chapterRepository;
    private ChapterMapper chapterMapper;
    private ChapterService chapterService;

    @BeforeEach
    public void setup() {
        chapterRepository = mock(ChapterRepository.class);
        chapterMapper = mock(ChapterMapper.class);
        chapterService = new ChapterService(chapterRepository, chapterMapper);
    }


    @Test
    void testGetallChapter() {
        Chapter chapter = new Chapter();
        chapter.setId(1L);
        chapter.setName("Chapter 1");

        ChapterDto dto = new ChapterDto();
        dto.setId(1L);
        dto.setName("Chapter 1");

        when(chapterRepository.findAll()).thenReturn(List.of(chapter));
        when(chapterMapper.toDto(chapter)).thenReturn(dto);

        List<ChapterDto> result = chapterService.getAllChapters();

        assertEquals(1, result.size());
        assertEquals("Chapter 1", result.get(0).getName());
        verify(chapterRepository, times(1)).findAll();
    }


    @Test
    void testGetbyidChapter() {
        Chapter chapter = new Chapter();
        chapter.setId(1L);
        chapter.setName("Test Chapter");

        ChapterDto dto = new ChapterDto();
        dto.setId(1L);
        dto.setName("Test Chapter");

        when(chapterRepository.findById(1L)).thenReturn(Optional.of(chapter));
        when(chapterMapper.toDto(chapter)).thenReturn(dto);

        ChapterDto result = chapterService.getChapterDtoById(1L);

        assertNotNull(result);
        assertEquals("Test Chapter", result.getName());
        verify(chapterRepository, times(1)).findById(1L);
    }


    @Test
    void testCreateChapter() {
        ChapterDto dto = new ChapterDto();
        dto.setName("New Chapter");

        Chapter chapter = new Chapter();
        chapter.setName("New Chapter");

        Chapter saved = new Chapter();
        saved.setId(10L);
        saved.setName("New Chapter");

        ChapterDto savedDto = new ChapterDto();
        savedDto.setId(10L);
        savedDto.setName("New Chapter");

        when(chapterMapper.toEntity(dto)).thenReturn(chapter);
        when(chapterRepository.save(chapter)).thenReturn(saved);
        when(chapterMapper.toDto(saved)).thenReturn(savedDto);

        ChapterDto result = chapterService.createChapter(dto);

        assertNotNull(result);
        assertEquals(10L, result.getId());
        verify(chapterRepository, times(1)).save(chapter);
    }


    @Test
    void testUpdateChapter() {
        Long id = 1L;

        Chapter existing = new Chapter();
        existing.setId(id);
        existing.setName("Old Name");

        ChapterDto updatedDto = new ChapterDto();
        updatedDto.setName("Updated Name");

        Chapter saved = new Chapter();
        saved.setId(id);
        saved.setName("Updated Name");

        ChapterDto resultDto = new ChapterDto();
        resultDto.setId(id);
        resultDto.setName("Updated Name");

        when(chapterRepository.findById(id)).thenReturn(Optional.of(existing));
        when(chapterRepository.save(existing)).thenReturn(saved);
        when(chapterMapper.toDto(saved)).thenReturn(resultDto);

        ChapterDto result = chapterService.updateChapter(id, updatedDto);

        assertEquals("Updated Name", result.getName());
        verify(chapterRepository).save(existing);
    }


    @Test
    void testDeleteChapter() {
        Long id = 1L;

        Chapter entity = new Chapter();
        entity.setId(id);

        when(chapterRepository.findById(id)).thenReturn(Optional.of(entity));

        chapterService.deleteChapter(id);

        verify(chapterRepository, times(1)).delete(entity);
    }






}
