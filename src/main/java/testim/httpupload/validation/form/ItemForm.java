package testim.httpupload.validation.form;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ItemForm {

    private Long itemId;
    private String itemName;
    private Integer price;
    private Integer quantity;
    private List<MultipartFile> imageFiles;
    private String itemType;

}
