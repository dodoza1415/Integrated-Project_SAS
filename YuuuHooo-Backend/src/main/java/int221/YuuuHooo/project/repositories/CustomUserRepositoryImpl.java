package int221.YuuuHooo.project.repositories;

import int221.YuuuHooo.project.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class CustomUserRepositoryImpl implements CustomUserRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void refresh(User user) {
        entityManager.refresh(user);
    }
}
