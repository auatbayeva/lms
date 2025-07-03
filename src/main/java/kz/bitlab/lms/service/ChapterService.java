package kz.bitlab.lms.service;

import kz.bitlab.lms.dto.ChapterDto;
import kz.bitlab.lms.entity.Chapter;
import kz.bitlab.lms.exception.ChapterNotFoundException;
import kz.bitlab.lms.mapper.ChapterMapper;
import kz.bitlab.lms.repository.ChapterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChapterService {

    private final ChapterRepository repository;
    private final ChapterMapper mapper;
    private static final Logger log = LoggerFactory.getLogger(ChapterService.class);

    public ChapterService(ChapterRepository repository, ChapterMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<ChapterDto> getAllChapters() {
        List<ChapterDto> chapters = repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        log.info("Retrieved {} chapter(s)", chapters.size());
        return chapters;
    }

    public ChapterDto createChapter(ChapterDto dto) {
        log.info("Creating new chapter: {}", dto.getName());
        Chapter chapter = mapper.toEntity(dto);
        Chapter saved = repository.save(chapter);
        return mapper.toDto(saved);
    }

    public Chapter getChapterById(Long id) {
        log.info("Fetching chapter by ID: {}", id);
        return repository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Chapter with ID {} not found", id);
                    return new ChapterNotFoundException("Chapter with ID " + id + " not found");
                });
    }

    public ChapterDto updateChapter(Long id, ChapterDto dto) {
        log.info("Updating chapter with ID: {}", id);
        Chapter chapter = getChapterById(id);
        chapter.setName(dto.getName());
        chapter.setDescription(dto.getDescription());
        Chapter updated = repository.save(chapter);
        log.info("Chapter updated successfully");
        return mapper.toDto(updated);
    }

    public void deleteChapter(Long id) {
        log.info("Deleting chapter with ID: {}", id);
        Chapter chapter = getChapterById(id);
        repository.delete(chapter);
        log.info("Chapter deleted successfully");
    }
    public ChapterDto getChapterDtoById(Long id) {
        Chapter chapter = getChapterById(id); // или отдельный приватный метод
        return mapper.toDto(chapter);
    }
}
