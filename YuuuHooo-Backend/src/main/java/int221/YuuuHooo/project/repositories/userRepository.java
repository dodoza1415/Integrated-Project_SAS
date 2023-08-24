package int221.YuuuHooo.project.repositories;

import int221.YuuuHooo.project.dtos.UserDTO;
import int221.YuuuHooo.project.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface userRepository extends JpaRepository<User,Integer>, CustomUserRepository {

}
