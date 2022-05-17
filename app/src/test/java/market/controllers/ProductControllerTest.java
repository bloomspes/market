package market.controllers;

import market.domain.Product;
import market.dto.ProductData;
import market.errors.ProductNotFoundException;
import market.application.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @BeforeEach
    void setUp() {
        Product product = Product.builder()
                .id(1L)
                .title("clean code")
                .publisher("insight")
                .build();

        given(productService.getProductList()).willReturn(List.of(product));
        given(productService.getProduct(1L)).willReturn(product);

        given(productService.getProduct(20L)).willThrow(new ProductNotFoundException(20L));

        given(productService.createProduct(any(ProductData.class))).willReturn(product);
    }

    @Test
    void 제품_목록을_가져오는데_성공한다() throws Exception {
        mockMvc.perform(get("/products").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("clean code")));
    }

    @Test
    void 제품의_상세_정보를_가져온다() throws Exception {
        mockMvc.perform(get("/products/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("clean code")));
    }

}