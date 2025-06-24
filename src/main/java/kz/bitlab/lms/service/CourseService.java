package kz.bitlab.lms.service;

import kz.bitlab.lms.dto.CourseDto;
import kz.bitlab.lms.entity.Course;
import kz.bitlab.lms.mapper.CourseMapper;
import kz.bitlab.lms.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private final CourseRepository repository;
    private final CourseMapper mapper;

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
        Course course = mapper.toEntity(dto);
        Course saved = repository.save(course);
        return mapper.toDto(saved);
    }
    public Course getCourseById(Long id) {
        return repository.findById(id).orElse(null);
    }

}
