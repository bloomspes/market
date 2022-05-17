package market.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class ProductData {
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String publisher;


}
