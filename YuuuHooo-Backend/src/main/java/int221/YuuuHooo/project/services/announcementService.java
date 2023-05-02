package int221.YuuuHooo.project.services;

import int221.YuuuHooo.project.entities.Announcement;
import int221.YuuuHooo.project.repositories.announcementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class announcementService {
    @Autowired
    private announcementRepository announcementRepository;

    public List<Announcement> getAnnouncement(){
        return announcementRepository.findAll(Sort.by("id").descending());
    }

    public Announcement getAnnouncementById(int id) {
        return announcementRepository.findById(id).orElseThrow(
                ()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Announcement id " + id + " does not exist"
                        ));
    }
}
