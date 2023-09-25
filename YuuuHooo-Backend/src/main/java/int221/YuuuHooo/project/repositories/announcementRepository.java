package int221.YuuuHooo.project.repositories;

import int221.YuuuHooo.project.entities.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(exported = false)
@Repository
public interface announcementRepository extends JpaRepository<Announcement, Integer> {

}
