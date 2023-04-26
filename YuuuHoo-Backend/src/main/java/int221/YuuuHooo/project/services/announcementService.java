package int221.YuuuHooo.project.services;

import int221.YuuuHooo.project.entities.Announcement;
import int221.YuuuHooo.project.repositories.announcementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class announcementService {
    @Autowired
    private announcementRepository announcementRepository;

    public List<Announcement> getAnnouncement(){
        return announcementRepository.findAll();
    }

    public Announcement getAnnouncementById(int idAnnouncement) {
        return announcementRepository.findById(idAnnouncement).orElseThrow(
                ()-> new RuntimeException(idAnnouncement + " does not exist !!!"));
    }
}
