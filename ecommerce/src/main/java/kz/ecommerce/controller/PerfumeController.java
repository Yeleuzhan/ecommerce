package kz.ecommerce.controller;

import kz.ecommerce.domain.Perfume;
import kz.ecommerce.dto.perfume.FullPerfumeResponse;
import kz.ecommerce.dto.perfume.PerfumeResponse;
import kz.ecommerce.mapper.PerfumeMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/perfumes")
public class PerfumeController {

    private final PerfumeMapper perfumeMapper;

    public PerfumeController(PerfumeMapper perfumeMapper) {
        this.perfumeMapper = perfumeMapper;
    }

    @GetMapping
    public ResponseEntity<List<PerfumeResponse>> getAllPerfumes() {
        return ResponseEntity.ok(perfumeMapper.getAllPerfumes());
    }

    @GetMapping("/{perfumeId}")
    public ResponseEntity<FullPerfumeResponse> getPerfumeById(@PathVariable Long perfumeId) {
        return ResponseEntity.ok(perfumeMapper.getPerfumeById(perfumeId));
    }

    @PostMapping("/ids")
    public ResponseEntity<List<PerfumeResponse>> getPerfumesByIds(@RequestBody List<Long> perfumeIds) {
        return ResponseEntity.ok(perfumeMapper.getPerfumesByIds(perfumeIds));
    }

}
