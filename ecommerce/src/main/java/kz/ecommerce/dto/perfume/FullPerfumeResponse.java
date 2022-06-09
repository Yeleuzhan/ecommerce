package kz.ecommerce.dto.perfume;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class FullPerfumeResponse extends PerfumeResponse {

    private Integer year;
    private String country;
    private String perfumeGender;
    private String fragranceTopNotes;
    private String fragranceMiddleNotes;
    private String fragranceBaseNotes;
    private String description;
    private String filename;
    private String volume;
    private String type;
    private MultipartFile file;

}
