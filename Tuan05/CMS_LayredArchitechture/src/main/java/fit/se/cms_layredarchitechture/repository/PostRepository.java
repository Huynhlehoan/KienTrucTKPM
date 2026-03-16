package fit.se.cms_layredarchitechture.repository;


import fit.se.cms_layredarchitechture.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}