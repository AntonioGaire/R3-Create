package es.gaire.r3create.repository;

import es.gaire.r3create.domain.Comment;
import es.gaire.r3create.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select u from User u where u.deleted = false order by size(u.posts) desc limit :top")
    public List<User> findTopUsers(@Param("top") int top);

    @Query(value = "select u from User u where u.deleted = false and u.username like :username")
    public Optional<User> findByUsername(@Param("username") String username);

    @Override
    @Query(value = "select u from User u where u.deleted = false")
    List<User> findAll();
    @Modifying(flushAutomatically = true)
    @Query(value = "update User u set u.deleted = true where u.idUser = :user")
    void delete(@Param("user") long user);
}
