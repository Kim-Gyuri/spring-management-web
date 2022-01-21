package testim.httpupload.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class Cart implements Serializable {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cartId;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cart")
    private Set<ProductInOrder> products = new HashSet<>();

    @Override
    public String toString() {
        return "Cart{" +
                "cartId=" + cartId +
                ", products=" + products +
                '}';
    }

    public Cart(User uer) {
        this.user = user;
    }





}
