package testim.httpupload.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name="users")
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;

    private String password;

    private String name;

    private String phone;

    private String address;

    private boolean active;

    private String role = "ROLE_CUSTOMER";

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Cart cart;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email +"'\'" +
                ", password='" + password + "'\'" +
                ", name='" + name + "'\'" +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", active='" + active +
                ", role='" + role +'\'' +
                '}';
    }




}
