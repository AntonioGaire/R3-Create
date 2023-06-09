package es.gaire.r3create.repository;

import es.gaire.r3create.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "select p from Post p where p.postType.idPostType = :id and p.deleted = false")
    public List<Post> findAllByPostType(@Param("id") Long id);

    @Query(value = "select p from Post p where p.deleted = false ")
    public List<Post> findAll();

    @Query(value = "select p from Post p where p.deleted = false ")
    public Page<Post> findAllPaginated(Pageable page);

    @Query(value = "select p from Post p join p.categories c where p.postType.name = :type and p.deleted=false and p.kudos >= :kudos")
    public Page<Post> findAllByPostTypePaginated(@Param("type")String type, @Param("kudos")int kudos, Pageable page);

    @Query(value = "select p from Post p join p.categories c where c.name in :categories and p.deleted=false and p.kudos >= :kudos")
    public Page<Post> findAllByCategoriesPaginated(@Param("categories")String[] categories, @Param("kudos")int kudos, Pageable page);

    @Query(value = "select p from Post p join p.categories c where p.postType.name = :type and c.name in :categories and p.deleted=false and p.kudos >= :kudos")
    public Page<Post> findAllByPostTypeAndCategoriesPaginated(@Param("type")String type,@Param("categories")String[] categories, @Param("kudos")int kudos, Pageable page);

    //@Query(value = "select p from Post p where p.title and p.deleted = false")
    public Page<Post> findByTitleContainingIgnoreCase(@Param("title")String title, Pageable page);
    @Modifying
    @Query(value = "update Post p set p.deleted = true where p.idPost = :post")
    void delete(@Param("post") long post);

}
