package testim.httpupload.domain;

import lombok.Getter;
import testim.httpupload.entity.item.Item;

import javax.persistence.*;

@Entity
@Getter
public class UploadFile {

    @Id
    @GeneratedValue
    @Column(name = "uploadFile_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private String uploadFileName;
    private String storeFileName;

    public UploadFile(String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }
}
