package es.gaire.r3create.repository;

import es.gaire.r3create.domain.Category;
import es.gaire.r3create.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
