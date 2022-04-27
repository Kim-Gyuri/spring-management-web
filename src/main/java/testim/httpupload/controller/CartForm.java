package testim.httpupload.controller;

import lombok.Getter;
import lombok.Setter;
import testim.httpupload.domain.Item;

@Getter @Setter
public class CartForm {

    private Long id;

    private int count;

    private Item item;
}
