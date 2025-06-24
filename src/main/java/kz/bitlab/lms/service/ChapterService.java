package kz.bitlab.lms.service;

import kz.bitlab.lms.dto.ChapterDto;
import kz.bitlab.lms.entity.Chapter;
import kz.bitlab.lms.mapper.ChapterMapper;
import kz.bitlab.lms.repository.ChapterRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChapterService {

    private final ChapterRepository repository;
    private final ChapterMapper mapper;

    public ChapterService(ChapterRepository repository, ChapterMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public List<ChapterDto> getAllChapters() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public ChapterDto createChapter(ChapterDto dto) {
        Chapter chapter = mapper.toEntity(dto);
        Chapter saved = repository.save(chapter);
        return mapper.toDto(saved);
    }
}