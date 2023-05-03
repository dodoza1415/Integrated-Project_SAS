package int221.YuuuHooo.project.controllers;

import int221.YuuuHooo.project.dtos.AddAnnouncementDTO;
import int221.YuuuHooo.project.dtos.AnnouncementByIdDTO;
import int221.YuuuHooo.project.dtos.AnnouncementDTO;
import int221.YuuuHooo.project.entities.Announcement;
import int221.YuuuHooo.project.services.announcementService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class announcementController {
    @Autowired
    private announcementService announcementService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/announcements")
    public List<AnnouncementDTO> getAnnouncement(){
        List<Announcement> announcementList = announcementService.getAnnouncement();
        List<AnnouncementDTO> announcementDTOList =
                announcementList.stream()
                        .map(a -> modelMapper.map(a, AnnouncementDTO.class))
                        .collect(Collectors.toList());
        return announcementDTOList;
    }

    @GetMapping("/announcements/{id}")
    public AnnouncementByIdDTO getAnnouncementById(@PathVariable Integer id) {
        Announcement announcement = announcementService.getAnnouncementById(id);
        AnnouncementByIdDTO announcementDTO = modelMapper.map(announcement, AnnouncementByIdDTO.class);
        return announcementDTO;

    }

//    @GetMapping("/announcements/list")
//    public List<AddAnnouncementDTO> getAllAnnouncement(){
//        List<Announcement> announcement = announcementService.getAllAnnouncement();
//        List <AddAnnouncementDTO> addAnnouncementDTO =
//                announcement.stream()
//                        .map(a -> modelMapper.map(a, AddAnnouncementDTO.class))
//                        .collect(Collectors.toList());
//        return addAnnouncementDTO;
//    }

    @PostMapping("/announcements")
    public Announcement addAnnouncement(@RequestBody AddAnnouncementDTO newAnnouncementDTO){
        Announcement newAnnouncement = modelMapper.map(newAnnouncementDTO, Announcement.class);
        return announcementService.addAnnouncement(newAnnouncement);
    }
}
