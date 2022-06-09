package kz.ecommerce.service;

import kz.ecommerce.domain.Perfume;

import java.util.List;

public interface PerfumeService {

    List<Perfume> getAllPerfumes();

    Perfume getPerfumeById(Long perfumeId);

    List<Perfume> getPerfumesByIds(List<Long> perfumesId);

}
