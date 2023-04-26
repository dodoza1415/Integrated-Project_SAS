package int221.YuuuHooo.project.controllers;

import int221.YuuuHooo.project.entities.Announcement;
import int221.YuuuHooo.project.services.announcementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class announcementController {
    @Autowired
    private announcementService announcementService;

    @GetMapping("/announcements")
    public List<Announcement> getAnnouncement(){
        return announcementService.getAnnouncement();
    }

    @GetMapping("/announcements/{idAnnouncement}")
    public Announcement getAnnouncementById(@PathVariable Integer idAnnouncement) {
        return announcementService.getAnnouncementById(idAnnouncement);
    }


}
