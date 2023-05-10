package es.gaire.r3create.dto;

import es.gaire.r3create.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    private Category category;
    private int nOfPosts;
}
