package kz.bitlab.lms.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.bitlab.lms.dto.ChapterDto;
import kz.bitlab.lms.service.ChapterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chapters")
@Tag(name = "Chapters", description = "Chapter management operations")
public class ChapterController {

    private final ChapterService chapterService;
    private static final Logger log = LoggerFactory.getLogger(ChapterController.class);

    public ChapterController(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    @GetMapping
    @Operation(summary = "Get All Chapters")
    public List<ChapterDto> getAllChapters() {
        log.info("GET /chapters");
        return chapterService.getAllChapters();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Chapter By ID")
    public ChapterDto getChapterById(@PathVariable Long id) {
        log.info("GET /chapters/{}", id);
        return chapterService.getChapterDtoById(id);
    }

    @PostMapping
    @Operation(summary = "Create Chapter")
    public ChapterDto createChapter(@RequestBody ChapterDto dto) {
        log.info("POST /chapters");
        return chapterService.createChapter(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Chapter")
    public ChapterDto updateChapter(@PathVariable Long id, @RequestBody ChapterDto dto) {
        log.info("PUT /chapters/{}", id);
        return chapterService.updateChapter(id, dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Chapter")
    public void deleteChapter(@PathVariable Long id) {
        log.info("DELETE /chapters/{}", id);
        chapterService.deleteChapter(id);
    }
}
