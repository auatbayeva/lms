package kz.bitlab.lms.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.bitlab.lms.dto.LessonDto;
import kz.bitlab.lms.service.LessonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lessons")
@Tag(name = "Lessons", description = "Lesson management operations")
public class LessonController {

    private final LessonService lessonService;
    private static final Logger log = LoggerFactory.getLogger(LessonController.class);

    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @GetMapping
    @Operation(summary = "Get All Lessons")
    public List<LessonDto> getAllLessons() {
        log.info("GET /lessons");
        return lessonService.getAllLessons();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Lesson By ID")
    public LessonDto getLessonById(@PathVariable Long id) {
        log.info("GET /lessons/{}", id);
        return lessonService.getLessonDtoById(id);
    }

    @PostMapping
    @Operation(summary = "Create Lesson")
    public LessonDto createLesson(@RequestBody LessonDto dto) {
        log.info("POST /lessons");
        return lessonService.createLesson(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Lesson")
    public LessonDto updateLesson(@PathVariable Long id, @RequestBody LessonDto dto) {
        log.info("PUT /lessons/{}", id);
        return lessonService.updateLesson(id, dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Lesson")
    public void deleteLesson(@PathVariable Long id) {
        log.info("DELETE /lessons/{}", id);
        lessonService.deleteLesson(id);
    }
}
