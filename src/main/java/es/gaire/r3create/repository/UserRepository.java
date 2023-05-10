package es.gaire.r3create.repository;

import es.gaire.r3create.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select u from User u where u.deleted = false order by size(u.posts) desc limit :top")
    public List<User> findTopUsers(@Param("top") int top);
}
