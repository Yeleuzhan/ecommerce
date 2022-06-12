package kz.ecommerce.service;

import kz.ecommerce.domain.Perfume;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PerfumeService {

    Perfume savePerfume(Perfume perfume, MultipartFile file);

    List<Perfume> getAllPerfumes();

    Perfume getPerfumeById(Long perfumeId);

    List<Perfume> getPerfumesByIds(List<Long> perfumesId);

    List<Perfume> findPerfumesByFilterParams(List<String> perfumers, List<String> genders, List<Integer> prices, boolean sortByPrice);

    List<Perfume> findByPerfumeGender(String perfumeGender);

    List<Perfume> findByPerfumer(String perfumer);

    String deletePerfume(Long perfumeId);

}
