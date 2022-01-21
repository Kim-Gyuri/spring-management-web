package testim.httpupload.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@DynamicUpdate
public class OrderMain implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "orderMain")
    private Set<ProductInOrder> products = new HashSet<>();

    private String buyerEmail;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private BigDecimal orderAmount;

    @NotNull
    @ColumnDefault("0")
    private Integer orderStatus;

    @CreationTimestamp
    private LocalDateTime createTime;

    @UpdateTimestamp
    private LocalDateTime updateTime;

    public OrderMain(User buyer) {
        this.buyerEmail = buyer.getEmail();
        this.buyerName = buyer.getName();
        this.buyerPhone = buyer.getPhone();
        this.buyerAddress = buyer.getAddress();
        this.orderAmount = buyer.getCart().getProducts().stream().map(item->item.getProductPrice().multiply(new BigDecimal(item.getCount())))
                .reduce(BigDecimal::add)
                .orElse(new BigDecimal(0));
        this.orderStatus = 0;

    }



}
