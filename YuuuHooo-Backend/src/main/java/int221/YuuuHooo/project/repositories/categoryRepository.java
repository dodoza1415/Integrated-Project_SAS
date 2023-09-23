package int221.YuuuHooo.project.repositories;

import int221.YuuuHooo.project.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface categoryRepository extends JpaRepository<Category, Integer> {
}
