package kz.ecommerce.service;

import kz.ecommerce.domain.Perfume;

import java.util.List;

public interface PerfumeService {

    List<Perfume> getAllPerfumes();

    Perfume getPerfumeById(Long perfumeId);

    List<Perfume> getPerfumesByIds(List<Long> perfumesId);

    List<Perfume> findPerfumesByFilterParams(List<String> perfumers, List<String> genders, List<Integer> prices, boolean sortByPrice);

    List<Perfume> findByPerfumeGender(String perfumeGender);

    List<Perfume> findByPerfumer(String perfumer);

}
