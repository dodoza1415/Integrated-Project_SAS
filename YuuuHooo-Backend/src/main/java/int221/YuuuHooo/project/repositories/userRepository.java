package int221.YuuuHooo.project.repositories;

import int221.YuuuHooo.project.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(exported = false)
@Repository
public interface userRepository extends JpaRepository<User,Integer>, CustomUserRepository {
    User findByUsername(String username);
    User findByName(String name);
    User findByEmail(String email);

}
