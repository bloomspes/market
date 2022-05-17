package market.application;

import market.dto.ProductMapper;
import market.domain.Product;
import market.domain.ProductRepository;
import market.dto.ProductData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ProductServiceTest {

    private ProductService productService;
    private final ProductRepository productRepository = mock(ProductRepository.class);

    @BeforeEach
    void setUp() {
        productService = new ProductService(productRepository);
        ProductData givenProductData = new ProductData(2L, "effective", "인사이트");

        Product product = ProductMapper.INSTANCE.productDataToProduct(givenProductData);
        productService = new ProductService(productRepository);

        given(productRepository.findAll()).willReturn(List.of(product));
        given(productRepository.findById(1L)).willReturn(Optional.of(product));

        given(productRepository.save(any(Product.class))).will(invocation -> {
            Product source = invocation.getArgument(0);

            return Product.builder()
                    .id(source.getId())
                    .title(source.getTitle())
                    .publisher(source.getPublisher())
                    .build();
        });
    }

    @Test
    void 제품을_생성하라() {
        // builder
        ProductData givenProductData = ProductData.builder()
                .id(2L)
                .title("effective")
                .publisher("인사이트")
                .build();

        Product product = productService.createProduct(givenProductData);

        verify(productRepository).save(any(Product.class));

        assertThat(product.getId()).isEqualTo(givenProductData.getId());
        assertThat(product.getTitle()).isEqualTo(givenProductData.getTitle());
        assertThat(product.getPublisher()).isEqualTo(givenProductData.getPublisher());
    }

}