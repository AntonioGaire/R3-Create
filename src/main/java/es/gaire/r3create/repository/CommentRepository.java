package es.gaire.r3create.repository;

import es.gaire.r3create.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Override
    @Query(value = "select c from Comment c where c.deleted = false")
    List<Comment> findAll();

    @Query(value = "select c from Comment c where c.idComment = :idComment and c.deleted = false")
    Optional<Comment> find(@Param("idComment") long idComment);

    @Modifying
    @Query(value = "update Comment c set c.deleted = true where c.idComment = :comment")
    void delete(@Param("comment") long comment);
}
