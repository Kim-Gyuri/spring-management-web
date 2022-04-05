package testim.httpupload.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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

    public Item(String itemName, Integer price, Integer quantity, List<UploadFile> imageFiles, String itemType) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
        this.imageFiles = imageFiles;
        this.itemType = itemType;
    }

    public Item() {

    }


}
