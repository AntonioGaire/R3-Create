package es.gaire.r3create.repository;

import es.gaire.r3create.domain.Comment;
import es.gaire.r3create.domain.PostType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TypeRepository extends JpaRepository<PostType, Long> {

    @Query(value = "select t from PostType t where t.name like :name")
    Optional<PostType> findByName(@Param("name") String name);
}
