package int221.YuuuHooo.project.controllers;

import int221.YuuuHooo.project.entities.Announcement;
import int221.YuuuHooo.project.services.announcementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/announcement")
public class announcementController {
    @Autowired
    private announcementService announcementService;

    @GetMapping("")
    public List<Announcement> getAnnouncement(){
        return announcementService.getAnnouncement();
    }
}
