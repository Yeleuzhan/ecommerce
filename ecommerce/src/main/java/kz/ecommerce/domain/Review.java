package kz.ecommerce.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "reviews")
@Data
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String author;

    private String message;

    private Integer rating;

    private LocalDate date;

    public Review() {
        date = LocalDate.now();
    }

}
