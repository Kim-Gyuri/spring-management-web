package testim.httpupload.entity.item;

import lombok.Builder;
import lombok.Getter;
import testim.httpupload.domain.UploadFile;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

@Entity
@DiscriminatorValue("A")
@Getter
public class Album extends Item{
    private String artist;
    private String etc;

    @Builder(builderMethodName = "child2")
    public Album(String artist, String etc, Long id, String itemName, Integer price, Integer quantity, List<UploadFile> imageFiles, String itemType) {
        super(id, itemName, price, quantity, imageFiles, itemType);
        this.artist = artist;
        this.etc = etc;
    }
}
