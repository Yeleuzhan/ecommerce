package kz.ecommerce.dto.perfume;

import lombok.Data;

@Data
public class PerfumeResponse {

    private Long id;
    private String perfumeTitle;
    private String perfumer;
    private String price;
    private Double perfumeRating;
    private String filename;

}
