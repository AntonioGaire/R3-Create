package es.gaire.r3create.request;

import es.gaire.r3create.domain.Category;
import es.gaire.r3create.domain.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {
    private String title;
    private String postType;
    private BigDecimal reward;
    private List<Category> categories;
    private String description;
    private List<Image> images;
    private String user;

}
