package int221.YuuuHooo.project.repositories;

import int221.YuuuHooo.project.entities.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface announcementRepository extends JpaRepository<Announcement, Integer> {

}
