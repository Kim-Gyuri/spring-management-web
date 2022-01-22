package testim.httpupload.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Cart {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;


    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();



    public Cart(User uer) {
        this.user = user;
    }





}
