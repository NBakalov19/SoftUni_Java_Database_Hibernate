package retake.instagraph.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import retake.instagraph.domain.entities.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

}
