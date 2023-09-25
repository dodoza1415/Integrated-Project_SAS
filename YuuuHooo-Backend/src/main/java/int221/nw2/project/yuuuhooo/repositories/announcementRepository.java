package int221.nw2.project.yuuuhooo.repositories;

import int221.nw2.project.yuuuhooo.entities.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(exported = false)
@Repository
public interface announcementRepository extends JpaRepository<Announcement, Integer> {

}
