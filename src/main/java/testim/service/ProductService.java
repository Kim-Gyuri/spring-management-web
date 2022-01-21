package testim.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import testim.httpupload.domain.ProductInfo;

public interface ProductService {

    ProductInfo findOne(Long productId);

    Page<ProductInfo> findUpAll(Pageable pageable);

    Page<ProductInfo> findAll(Pageable pageable);

    Page<ProductInfo> findAllCategory(Integer categoryType, Pageable pageable);

    void increaseStock(String productId, int amount);

    void decreaseStock(String productId, int amount);

    ProductInfo offSale(String productId);

    ProductInfo onSale(String productId);

    ProductInfo update(ProductInfo productInfo);

    ProductInfo save(ProductInfo productInfo);

    void delete(String productId);

}
