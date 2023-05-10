package es.gaire.r3create.repository;

import es.gaire.r3create.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "select c from Category c order by size(c.posts) desc limit :top")
    public List<Category> findTopCategories(@Param("top") int top);

    @Query(value = "select c, size(c.posts)  from Category c ")
    public List<Object[]> findAllCategoriesWNofPosts();
}
