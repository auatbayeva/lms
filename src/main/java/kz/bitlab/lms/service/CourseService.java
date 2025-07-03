package kz.bitlab.lms.service;

import kz.bitlab.lms.dto.CourseDto;
import kz.bitlab.lms.entity.Course;
import kz.bitlab.lms.exception.CourseNotFoundException;
import kz.bitlab.lms.mapper.CourseMapper;
import kz.bitlab.lms.repository.CourseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private final CourseRepository repository;
    private final CourseMapper mapper;
    private static final Logger log = LoggerFactory.getLogger(CourseService.class);


    public CourseService(CourseRepository courseRepository,CourseMapper mapper) {
        this.repository = courseRepository;
        this.mapper = mapper;
    }

    public List<CourseDto> getAllCourses() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public CourseDto createCourse(CourseDto dto) {
        log.info("Creating new course with name: {}", dto.getName());
        Course course = mapper.toEntity(dto);
        Course saved = repository.save(course);
        log.debug("Course created with ID: {}", saved.getId());
        return mapper.toDto(saved);
    }

    public Course getCourseById(Long id) {
        log.info("Looking for course with ID: {}", id);
        return repository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Course with ID {} not found", id);
                    return new CourseNotFoundException("Course with ID " + id + " not found");
                });
    }

    public CourseDto updateCourse(Long id, CourseDto dto) {
        log.info("Updating course with ID: {}", id);
        Course course = getCourseById(id); // throws exception if not found

        log.debug("Old values: name='{}', description='{}'", course.getName(), course.getDescription());
        log.debug("New values: name='{}', description='{}'", dto.getName(), dto.getDescription());

        course.setName(dto.getName());
        course.setDescription(dto.getDescription());

        Course updated = repository.save(course);
        log.info("Course updated successfully with ID: {}", updated.getId());
        return mapper.toDto(updated);
    }

    public void deleteCourse(Long id) {
        log.info("Deleting course with ID: {}", id);
        Course course = getCourseById(id); // throws exception if not found
        repository.delete(course);
        log.info("Course with ID {} deleted successfully", id);
    }

    public CourseDto getCourseDtoById(Long id) {
        return mapper.toDto(getCourseById(id));
    }


}
