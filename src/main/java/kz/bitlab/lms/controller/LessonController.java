package kz.bitlab.lms.controller;

import kz.bitlab.lms.dto.LessonDto;
import kz.bitlab.lms.service.LessonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lessons")
public class LessonController {

    private final LessonService lessonService;

    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @GetMapping
    public List<LessonDto> getAllLessons() {
        return lessonService.getAllLessons();
    }

    @PostMapping
    public LessonDto createLesson(@RequestBody LessonDto dto) {
        return lessonService.createLesson(dto);
    }
}
