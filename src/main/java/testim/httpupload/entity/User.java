package testim.httpupload.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name="users")
@Getter
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @NotEmpty
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

    private String phone;

    @Embedded
    private String address;

    private boolean active;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Cart cart;

    @OneToMany(mappedBy = "user")
    private List<Order> orders= new ArrayList<>();

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
                '}';
    }




}
