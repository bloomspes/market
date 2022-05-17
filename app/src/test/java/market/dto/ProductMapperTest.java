package market.dto;


import market.domain.Product;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProductMapperTest {
    @Test
    public void shouldMapProductToDTO() {
        // given
        ProductData givenProductData = ProductData.builder()
                .id(2L)
                .title("effective")
                .publisher("insight")
                .build();

        // when
        Product product = ProductMapper.INSTANCE.productDataToProduct(givenProductData);

        // then
        assertThat(product.getId()).isNotNull();
        assertThat(product.getTitle()).isEqualTo("clean code");
        assertThat(product.getPublisher()).isEqualTo("insight");
    }

}