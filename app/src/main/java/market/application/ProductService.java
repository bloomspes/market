package market.application;

import market.dto.ProductMapper;
import market.domain.Product;
import market.dto.ProductData;
import market.errors.ProductNotFoundException;
import market.domain.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository =  productRepository;
    }

    public List<Product> getProductList() {
        return productRepository.findAll();
    }

    public Product getProduct(Long id) {
        return findProduct(id);
    }

    private Product findProduct(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    public Product createProduct(ProductData productData) {
        Product product = ProductMapper.INSTANCE.productDataToProduct(productData);
        return productRepository.save(product);
    }

    
}
