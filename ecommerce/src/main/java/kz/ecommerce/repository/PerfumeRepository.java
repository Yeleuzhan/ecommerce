package kz.ecommerce.repository;

import kz.ecommerce.domain.Perfume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerfumeRepository extends JpaRepository<Perfume, Long> {

    List<Perfume> findAllByOrderByIdAsc();

    List<Perfume> findByIdIn(List<Long> perfumeIds);

    @Query("SELECT perfume FROM Perfume perfume " +
            "WHERE (:perfumers IS NULL OR perfume.perfumer IN :perfumers) " +
            "AND (:genders IS NULL OR perfume.perfumeGender IN :genders) " +
            "AND (:priceStart IS NULL OR perfume.price BETWEEN :priceStart AND :priceEnd) " +
            "ORDER BY CASE WHEN :sortByPrice = true THEN perfume.price ELSE -perfume.price END ASC")
    List<Perfume> findPerfumesByFilterParams(
            List<String> perfumers,
            List<String> genders,
            Integer priceStart,
            Integer priceEnd,
            boolean sortByPrice
    );

}
