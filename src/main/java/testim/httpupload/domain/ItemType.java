package testim.httpupload.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
/*
HIGHEST("최상"), BEST("상"), LOWER("중")
 */
@Data
@AllArgsConstructor
public class ItemType {
    private String code;
    private String typeStatus;
}
