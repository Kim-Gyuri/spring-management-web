package testim.httpupload.domain;

import lombok.Getter;
import lombok.Setter;
import testim.httpupload.exception.NotEnoughStockException;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Entity
@Getter @Setter
public class Item {

    @Id @GeneratedValue
    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;

    @Embedded
    private List<UploadFile> imageFiles;

    private String itemType;

    private Integer categoryType;

    public Item(String itemName, Integer price, Integer quantity, List<UploadFile> imageFiles, String itemType, Integer categoryType) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
        this.imageFiles = imageFiles;
        this.itemType = itemType;
        this.categoryType = categoryType;
    }

    public Item() {

    }



        //==비지니스 로직==//
    /*
    stock 증가
     */
    public void addStock(int quantity) {
        this.quantity += quantity;
    }

    /*
    stock 감소
     */
    public void removeStock(int quantity) {
        int restQuantity = this.quantity - quantity;
        if (restQuantity < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.quantity = restQuantity;
    }

}
