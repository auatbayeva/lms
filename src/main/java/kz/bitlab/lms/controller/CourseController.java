package kz.bitlab.lms.controller;

import kz.bitlab.lms.dto.CourseDto;
import kz.bitlab.lms.entity.Course;
import kz.bitlab.lms.mapper.CourseMapper;
import kz.bitlab.lms.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;
    private final CourseMapper courseMapper;

    public CourseController(CourseService courseService, CourseMapper courseMapper) {
        this.courseService = courseService;
        this.courseMapper = courseMapper;
    }

    @GetMapping
    public List<CourseDto> getAllCourses() {
        System.out.println(courseService.getAllCourses());
        return courseService.getAllCourses();
    }
    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> getCourse(@PathVariable Long id) {
        Course course = courseService.getCourseById(id);
        return ResponseEntity.ok(courseMapper.toDto(course)); // именно DTO
    }

    @PostMapping
    public CourseDto createCourse(@RequestBody CourseDto dto) {
        return courseService.createCourse(dto);
    }
}