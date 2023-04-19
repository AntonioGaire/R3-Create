package es.gaire.r3create.repository;

import es.gaire.r3create.domain.PostType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<PostType, Long> {
}
