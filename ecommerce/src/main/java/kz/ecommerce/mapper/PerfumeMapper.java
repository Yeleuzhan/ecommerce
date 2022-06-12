package kz.ecommerce.mapper;

import kz.ecommerce.domain.Perfume;
import kz.ecommerce.dto.perfume.FullPerfumeResponse;
import kz.ecommerce.dto.perfume.PerfumeRequest;
import kz.ecommerce.dto.perfume.PerfumeResponse;
import kz.ecommerce.dto.perfume.PerfumeSearchRequest;
import kz.ecommerce.exception.ApiRequestException;
import kz.ecommerce.exception.InputFieldException;
import kz.ecommerce.service.PerfumeService;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

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

    public List<PerfumeResponse> findPerfumesByFilterParams(PerfumeSearchRequest filter) {
        List<Perfume> perfumeList = perfumeService.findPerfumesByFilterParams(
                filter.getPerfumers(),
                filter.getGenders(),
                filter.getPrices(),
                filter.isSortByPrice()
        );
        return commonMapper.convertToResponseList(perfumeList, PerfumeResponse.class);
    }

    public List<PerfumeResponse> findByPerfumeGender(String perfumeGender) {
        return commonMapper.convertToResponseList(perfumeService.findByPerfumeGender(perfumeGender), PerfumeResponse.class);
    }

    public List<PerfumeResponse> findByPerfumer(String perfumer) {
        return commonMapper.convertToResponseList(perfumeService.findByPerfumer(perfumer), PerfumeResponse.class);
    }

    public FullPerfumeResponse savePerfume(PerfumeRequest perfumeRequest, MultipartFile file, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InputFieldException(bindingResult);
        }
        Perfume perfume = commonMapper.convertToEntity(perfumeRequest, Perfume.class);
        return commonMapper.convertToResponse(perfumeService.savePerfume(perfume, file), FullPerfumeResponse.class);
    }

    public String deletePerfume(Long perfumeId) {
        return perfumeService.deletePerfume(perfumeId);
    }

}
