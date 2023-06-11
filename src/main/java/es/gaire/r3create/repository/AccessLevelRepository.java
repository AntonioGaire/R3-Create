package es.gaire.r3create.repository;

import es.gaire.r3create.domain.AccessLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccessLevelRepository extends JpaRepository<AccessLevel, Long> {

    Optional<AccessLevel> findByName( String name );
}