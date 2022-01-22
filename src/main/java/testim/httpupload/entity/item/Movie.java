package testim.httpupload.entity.item;

import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("M")
@Getter
public class Movie {
    private String director;
    private String actor;
}
