package testim.httpupload.entity.item;

import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B")
@Getter
public class Book {
    private String author;
    private String isbn;
}
