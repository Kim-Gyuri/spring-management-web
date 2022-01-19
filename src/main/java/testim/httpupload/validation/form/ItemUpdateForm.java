package testim.httpupload.validation.form;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ItemUpdateForm {

    private Long id;

    private String itemName;

    private Integer price;

    private Integer quantity;

    private String itemType;

    private List<MultipartFile> imageFiles;
}
