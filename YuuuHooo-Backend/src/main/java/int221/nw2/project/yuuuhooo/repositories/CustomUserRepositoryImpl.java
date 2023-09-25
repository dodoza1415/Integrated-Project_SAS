package int221.nw2.project.yuuuhooo.repositories;

import int221.nw2.project.yuuuhooo.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(exported = false)
@Repository
public class CustomUserRepositoryImpl implements CustomUserRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void refresh(User user) {
        entityManager.refresh(user);
    }
}
