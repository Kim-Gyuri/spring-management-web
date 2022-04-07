package testim.httpupload.vo.response;

import org.springframework.data.domain.Page;
import testim.httpupload.domain.Item;

public class CategoryPage {

    private String category;
    private Page<Item> page;

    public CategoryPage(String category, Page<Item> page) {
        this.category = category;
        this.page = page;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Page<Item> getPage() {
        return page;
    }

    public void setPage(Page<Item> page) {
        this.page = page;
    }
}
