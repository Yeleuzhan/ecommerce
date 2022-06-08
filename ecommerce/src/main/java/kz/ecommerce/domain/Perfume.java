package kz.ecommerce.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "perfumes")
@Data
public class Perfume {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "perfume_id_seq")
    @SequenceGenerator(name = "perfume_id_seq", sequenceName = "perfume_id_seq", initialValue = 109, allocationSize = 1)
    private Long id;

    @Column(name = "perfume_title")
    private String perfumeTitle;

    private String perfumer;

    private Integer year;

    private String country;

    @Column(name = "perfume_gender")
    private String perfumeGender;

    @Column(name = "fragrance_top_notes")
    private String fraqranceTopNotes;

    @Column(name = "fragrance_middle_notes")
    private String fraqranceMiddleNotes;

    @Column(name = "fragrance_base_notes")
    private String fraqranceBaseNotes;

    private String description;

    private String filename;

    private Integer price;

    private String volume;

    private String type;

    private Double perfumeRating;

    @OneToMany
    private List<Review> reviews;

}
