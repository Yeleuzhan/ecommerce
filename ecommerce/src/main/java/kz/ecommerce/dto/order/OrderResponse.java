package kz.ecommerce.dto.order;

import lombok.Data;

import java.time.LocalDate;

@Data
public class OrderResponse {

    private Long id;
    private Double totalPrice;
    private LocalDate date;
    private String firstName;
    private String lastName;
    private String city;
    private String address;
    private String email;
    private String phoneNumber;
    private Integer postIndex;

}
