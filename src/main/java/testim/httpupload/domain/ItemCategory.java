package testim.httpupload.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Getter @Setter
public class ItemCategory implements Serializable {

    @Id @GeneratedValue
    private Long categoryId;

    private String categoryName;

    public ItemCategory() {}

    public ItemCategory(String categoryName) {
        this.categoryName = categoryName;
    }
}
