package kz.bitlab.lms.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import kz.bitlab.lms.dto.CourseDto;
import kz.bitlab.lms.entity.Course;
import kz.bitlab.lms.mapper.CourseMapper;
import kz.bitlab.lms.repository.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {

    private CourseRepository courseRepository;
    private CourseMapper courseMapper;
    private CourseService courseService;

    @BeforeEach
    public void setup() {
        courseRepository = mock(CourseRepository.class);
        courseMapper = mock(CourseMapper.class);
        courseService = new CourseService(courseRepository, courseMapper);
    }


    @Test
    void testGetallCourse() {
        Course course = new Course();
        course.setId(1L);
        course.setName("Course 1");

        CourseDto dto = new CourseDto();
        dto.setId(1L);
        dto.setName("Course 1");

        when(courseRepository.findAll()).thenReturn(List.of(course));
        when(courseMapper.toDto(course)).thenReturn(dto);

        List<CourseDto> result = courseService.getAllCourses();

        assertEquals(1, result.size());
        assertEquals("Course 1", result.get(0).getName());
        verify(courseRepository, times(1)).findAll();
    }


    @Test
    void testGetbyidCourse() {
        Course course = new Course();
        course.setId(1L);
        course.setName("Test Course");

        CourseDto dto = new CourseDto();
        dto.setId(1L);
        dto.setName("Test Course");

        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        when(courseMapper.toDto(course)).thenReturn(dto);

        CourseDto result = courseService.getCourseDtoById(1L);

        assertNotNull(result);
        assertEquals("Test Course", result.getName());
        verify(courseRepository, times(1)).findById(1L);
    }


    @Test
    void testCreateCourse() {
        CourseDto dto = new CourseDto();
        dto.setName("New Course");

        Course course = new Course();
        course.setName("New Course");

        Course saved = new Course();
        saved.setId(10L);
        saved.setName("New Course");

        CourseDto savedDto = new CourseDto();
        savedDto.setId(10L);
        savedDto.setName("New Course");

        when(courseMapper.toEntity(dto)).thenReturn(course);
        when(courseRepository.save(course)).thenReturn(saved);
        when(courseMapper.toDto(saved)).thenReturn(savedDto);

        CourseDto result = courseService.createCourse(dto);

        assertNotNull(result);
        assertEquals(10L, result.getId());
        verify(courseRepository, times(1)).save(course);
    }


    @Test
    void testUpdateCourse() {
        Long id = 1L;

        Course existing = new Course();
        existing.setId(id);
        existing.setName("Old Name");

        CourseDto updatedDto = new CourseDto();
        updatedDto.setName("Updated Name");

        Course saved = new Course();
        saved.setId(id);
        saved.setName("Updated Name");

        CourseDto resultDto = new CourseDto();
        resultDto.setId(id);
        resultDto.setName("Updated Name");

        when(courseRepository.findById(id)).thenReturn(Optional.of(existing));
        when(courseRepository.save(existing)).thenReturn(saved);
        when(courseMapper.toDto(saved)).thenReturn(resultDto);

        CourseDto result = courseService.updateCourse(id, updatedDto);

        assertEquals("Updated Name", result.getName());
        verify(courseRepository).save(existing);
    }


    @Test
    void testDeleteCourse() {
        Long id = 1L;

        Course entity = new Course();
        entity.setId(id);

        when(courseRepository.findById(id)).thenReturn(Optional.of(entity));

        courseService.deleteCourse(id);

        verify(courseRepository, times(1)).delete(entity);
    }



}
