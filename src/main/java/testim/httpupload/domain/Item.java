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
}
