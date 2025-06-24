package kz.bitlab.lms.controller;

import kz.bitlab.lms.dto.ChapterDto;
import kz.bitlab.lms.service.ChapterService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chapters")
public class ChapterController {

    private final ChapterService chapterService;

    public ChapterController(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    @GetMapping
    public List<ChapterDto> getAllChapters() {
        return chapterService.getAllChapters();
    }

    @PostMapping
    public ChapterDto createChapter(@RequestBody ChapterDto dto) {
        return chapterService.createChapter(dto);
    }
}
