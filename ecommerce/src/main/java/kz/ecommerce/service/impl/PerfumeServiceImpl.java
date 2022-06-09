package kz.ecommerce.service.impl;

import kz.ecommerce.domain.Perfume;
import kz.ecommerce.exception.ApiRequestException;
import kz.ecommerce.repository.PerfumeRepository;
import kz.ecommerce.service.PerfumeService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerfumeServiceImpl implements PerfumeService {

    private final PerfumeRepository perfumeRepository;

    public PerfumeServiceImpl(PerfumeRepository perfumeRepository) {
        this.perfumeRepository = perfumeRepository;
    }

    @Override
    public List<Perfume> getAllPerfumes() {
        return perfumeRepository.findAllByOrderByIdAsc();
    }

    @Override
    public Perfume getPerfumeById(Long perfumeId) {
        return perfumeRepository.findById(perfumeId)
                .orElseThrow(() -> new ApiRequestException("Perfume not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public List<Perfume> getPerfumesByIds(List<Long> perfumesId) {
        return perfumeRepository.findByIdIn(perfumesId);
    }
}