package testim.httpupload.entity.item;

import lombok.Builder;
import lombok.Getter;
import testim.httpupload.domain.UploadFile;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

@Entity
@DiscriminatorValue("M")
@Getter
public class Movie extends Item{
    private String director;
    private String actor;

    @Builder(builderMethodName = "child3")
    public Movie(String director, String actor, Long id, String itemName, Integer price, Integer quantity, List<UploadFile> imageFiles, String itemType) {
        super(id, itemName, price, quantity, imageFiles, itemType);
        this.director = director;
        this.actor = actor;
    }
}
