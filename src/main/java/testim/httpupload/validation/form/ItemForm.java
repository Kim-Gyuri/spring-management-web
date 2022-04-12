package testim.httpupload.validation.form;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class ItemForm {

    private Long itemId;
    private String itemName;
    private Integer price;
    private Integer quantity;
    private List<MultipartFile> imageFiles;
    private String itemType;
    private Integer categoryType;

}
