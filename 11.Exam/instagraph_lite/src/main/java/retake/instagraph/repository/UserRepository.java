package retake.instagraph.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import retake.instagraph.domain.entities.User;

import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

  User findByUsername(String username);

  @Query("select u from User u " +
          "join Post p on u.id = p.user " +
          "order by size(u.posts) desc, u.id")
  Set<User> findAllByPosts();
}
