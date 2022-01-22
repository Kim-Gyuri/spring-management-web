package testim.httpupload.entity.item;

import lombok.Getter;
import testim.httpupload.entity.Category;
import testim.httpupload.exception.NotEnoughStockException;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="dtype")
@Getter
public abstract class Item {

    @Id @GeneratedValue
    @Column(name="item_id")
    private Long id;

    private String name;
    private Integer price;
    private Integer stockQuantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id")
    private Category category;

    public void addStock(int quantity) {
        this.stockQuantity += stockQuantity;
    }

    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("need more stockQuantity");
        }
    }

}
