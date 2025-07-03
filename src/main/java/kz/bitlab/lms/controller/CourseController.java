package kz.bitlab.lms.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.bitlab.lms.dto.CourseDto;
import kz.bitlab.lms.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
@Tag(name = "Courses", description = "Course management operations")
public class CourseController {

    private final CourseService courseService;
    private static final Logger log = LoggerFactory.getLogger(CourseController.class);

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    @Operation(summary = "Get All Courses")
    public List<CourseDto> getAllCourses() {
        log.info("GET /courses");
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Course by ID")
    public ResponseEntity<CourseDto> getCourse(@PathVariable Long id) {
        log.info("GET /courses/{}", id);
        return ResponseEntity.ok(courseService.getCourseDtoById(id));
    }

    @PostMapping
    @Operation(summary = "Create Course")
    public ResponseEntity<CourseDto> createCourse(@RequestBody CourseDto dto) {
        log.info("POST /courses");
        log.debug("Course DTO received: {}", dto);
        return ResponseEntity.ok(courseService.createCourse(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Course")
    public ResponseEntity<CourseDto> updateCourse(@PathVariable Long id, @RequestBody CourseDto dto) {
        log.info("PUT /courses/{}", id);
        return ResponseEntity.ok(courseService.updateCourse(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Course")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        log.info("DELETE /courses/{}", id);
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}
