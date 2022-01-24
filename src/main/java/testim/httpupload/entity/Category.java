package testim.httpupload.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class Category {
    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

  //  private Integer categoryType;

    //@OneToMany(mappedBy = "category")
  //  private List<Item> items = new ArrayList<Item>();

}
