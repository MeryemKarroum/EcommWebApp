package meryem.userservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;

@Getter
@Setter
@ToString(exclude = "role")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Transactional
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    private String email;
    @ManyToOne
    private Role role;

    public Integer getId() {
        return id;
    }
}
