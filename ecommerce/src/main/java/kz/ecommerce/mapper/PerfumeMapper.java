package kz.ecommerce.mapper;

import kz.ecommerce.dto.perfume.FullPerfumeResponse;
import kz.ecommerce.dto.perfume.PerfumeResponse;
import kz.ecommerce.service.PerfumeService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PerfumeMapper {

    private final CommonMapper commonMapper;
    private final PerfumeService perfumeService;

    public PerfumeMapper(CommonMapper commonMapper, PerfumeService perfumeService) {
        this.commonMapper = commonMapper;
        this.perfumeService = perfumeService;
    }

    public List<PerfumeResponse> getAllPerfumes() {
        return commonMapper.convertToResponseList(perfumeService.getAllPerfumes(), PerfumeResponse.class);
    }

    public FullPerfumeResponse getPerfumeById(Long perfumeId) {
        return commonMapper.convertToResponse(perfumeService.getPerfumeById(perfumeId), FullPerfumeResponse.class);
    }

    public List<PerfumeResponse> getPerfumesByIds(List<Long> perfumesId) {
        return commonMapper.convertToResponseList(perfumeService.getPerfumesByIds(perfumesId), PerfumeResponse.class);
    }

}
