package testim.httpupload.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "user")
public class User {

    @Id @GeneratedValue
    private long id;

    private String loginId;
    private String password;

    private String name;

    private String email;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "user")
    private List<Order> orders = new ArrayList<>();


    public User(String loginId, String password, String name) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
    }

}
