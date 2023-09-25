package int221.YuuuHooo.project.repositories;

import int221.YuuuHooo.project.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(exported = false)
@Repository
public interface categoryRepository extends JpaRepository<Category, Integer> {
}
