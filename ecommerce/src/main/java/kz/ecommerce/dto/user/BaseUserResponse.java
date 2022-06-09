package kz.ecommerce.dto.user;

import kz.ecommerce.domain.enums.Role;
import lombok.Data;

import java.util.Set;

@Data
public class BaseUserResponse {

    private Long id;
    private String email;
    private String firstName;
    private Set<Role> roles;
    private String provider;

}
