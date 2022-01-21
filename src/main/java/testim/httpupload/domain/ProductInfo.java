package testim.httpupload.domain;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@DynamicUpdate
public class ProductInfo implements Serializable {

    @Id
    private String productId;

    private String productName;

    private BigDecimal productPrice;

    private Integer productStock;

    private String productDescription;

    private String productIcon;

    @ColumnDefault("0")
    private Integer productStatus;

    @ColumnDefault("0")
    private Integer categoryType;

    @CreationTimestamp
    private Date CreateTime;

    @UpdateTimestamp
    private Date updateTime;

    public ProductInfo() { }




}
