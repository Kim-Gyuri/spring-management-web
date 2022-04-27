package testim.httpupload.validation.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
@ToString
public class ItemForm {

    private long id;

    private String itemName;
    private Integer price;
    private Integer quantity;

    private List<MultipartFile> imageFiles;

    private String itemType;

    private Integer categoryType;



}
