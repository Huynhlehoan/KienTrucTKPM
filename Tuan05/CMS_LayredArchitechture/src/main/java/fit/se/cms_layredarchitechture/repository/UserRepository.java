package fit.se.cms_layredarchitechture.repository;
import fit.se.cms_layredarchitechture.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User, Long> {}
