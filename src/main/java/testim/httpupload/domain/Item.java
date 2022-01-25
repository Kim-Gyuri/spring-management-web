package testim.httpupload.domain;

import lombok.Data;

import java.util.List;

@Data
public class Item {

    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;
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
