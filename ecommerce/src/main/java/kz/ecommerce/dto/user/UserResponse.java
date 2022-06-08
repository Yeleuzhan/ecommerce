package kz.ecommerce.dto.user;

import lombok.Data;

@Data
public class UserResponse extends BaseUserResponse {

    private String lastName;
    private String city;
    private String address;
    private String phoneNumber;
    private String postIndex;

}
