package meryem.userservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegister {
    private String username;
    private String password;
    private String email;
//    private String roleName = "CLIENT";
    private Long roleId;
}