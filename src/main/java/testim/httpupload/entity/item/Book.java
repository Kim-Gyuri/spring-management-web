package testim.httpupload.entity.item;

import lombok.Builder;
import lombok.Getter;
import testim.httpupload.domain.UploadFile;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

@Entity
@DiscriminatorValue("B")
@Getter
public class Book extends Item{
    private String author;
    private String isbn;

    @Builder(builderMethodName = "child1")
    public Book(String author, String isbn,Long id, String itemName, Integer price, Integer quantity, List<UploadFile> imageFiles, String itemType) {
        super(id, itemName, price, quantity, imageFiles, itemType);
        this.author = author;
        this.isbn = isbn;
    }

}
