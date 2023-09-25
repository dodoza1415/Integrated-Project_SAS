package int221.nw2.project.yuuuhooo.repositories;

import int221.nw2.project.yuuuhooo.entities.User;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface CustomUserRepository {
    void refresh(User user);
}
