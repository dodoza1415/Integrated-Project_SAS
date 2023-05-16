package int221.YuuuHooo.project.repositories;

import int221.YuuuHooo.project.entities.Announcement;
import int221.YuuuHooo.project.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.ZonedDateTime;
import java.util.List;

public interface announcementRepository extends JpaRepository<Announcement, Integer> {

}
