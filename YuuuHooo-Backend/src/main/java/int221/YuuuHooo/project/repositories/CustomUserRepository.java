package int221.YuuuHooo.project.repositories;

import int221.YuuuHooo.project.entities.User;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface CustomUserRepository {
    void refresh(User user);
}
