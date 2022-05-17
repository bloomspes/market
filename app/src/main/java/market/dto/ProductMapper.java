package market.dto;

import market.domain.Product;
import org.mapstruct.Mapper;

import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    Product productDataToProduct(ProductData productData);

}
