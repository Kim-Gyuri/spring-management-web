package testim.httpupload.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
public class ProductInOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id")
    private OrderMain orderMain;

    private String productId;

    private String productName;

    private String productDescription;

    private String productIcon;

    private Integer categoryType;

    private BigDecimal productPrice;

    private Integer productStock;

    private Integer count;

    public ProductInOrder(ProductInfo productInfo, Integer quantity) {
        this.productId = productInfo.getProductId();
        this.productName =productInfo.getProductName();
        this.productDescription = productInfo.getProductDescription();
        this.productIcon = productInfo.getProductIcon();
        this.categoryType = productInfo.getCategoryType();
        this.productPrice = productInfo.getProductPrice();
        this.productStock = productInfo.getProductStock();
        this.count = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ProductInOrder inorder = (ProductInOrder) o;
        return Objects.equals(id, inorder.productId) &&
                Objects.equals(productId, inorder.productId) &&
                Objects.equals(productName, inorder.productName) &&
                Objects.equals(productDescription, inorder.productDescription) &&
                Objects.equals(productIcon, inorder.productIcon) &&
                Objects.equals(categoryType, inorder.categoryType) &&
                Objects.equals(productPrice, inorder.productPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, productId, productName, productDescription, productIcon, categoryType, productPrice);
    }
}
