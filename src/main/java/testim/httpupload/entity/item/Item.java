package testim.httpupload.entity.item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import testim.httpupload.domain.UploadFile;
import testim.httpupload.exception.NotEnoughStockException;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="dtype")
@Getter
@Builder
@AllArgsConstructor
public class Item implements Serializable {

    @Id @GeneratedValue
    @Column(name="item_id")
    private Long id;

    private String itemName;
    private Integer price;
    private Integer quantity;

    @OneToMany(mappedBy = "item")
    private List<UploadFile> imageFiles = new ArrayList<>();

    private String itemType;

    /*
        @Builder
        public Item(String itemName, Integer price, Integer quantity) {
            this.itemName = itemName;
            this.price = price;
            this.quantity = quantity;
        }
    */
    /*
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name="category_id")
        private Category category;
    */
    public void addStock(int quantity) {
        this.quantity += quantity;
    }

    public void removeStock(int quantity) {
        int restStock = this.quantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("need more stockQuantity");
        }
    }
/*
    @Builder(builderMethodName = "IBuilder")
    public Item(Long id, String itemName, Integer price, Integer quantity, Category category, List<UploadFile> imageFiles, String itemType) {
        this.id = id;
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.imageFiles = imageFiles;
        this.itemType = itemType;
    }

    public Item() {

    }

    @Builder
    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;

    }
*/
}
