package int221.YuuuHooo.project.controllers;

import int221.YuuuHooo.project.dtos.AddAnnouncementDTO;
import int221.YuuuHooo.project.dtos.AnnouncementByIdDTO;
import int221.YuuuHooo.project.dtos.AnnouncementDTO;
import int221.YuuuHooo.project.entities.Announcement;
import int221.YuuuHooo.project.entities.Category;
import int221.YuuuHooo.project.services.announcementService;
import int221.YuuuHooo.project.services.categoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:5173", "http://intproj22.sit.kmutt.ac.th"})
public class announcementController {
    @Autowired
    private announcementService announcementService;

    @Autowired
    private categoryService categoryService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/announcements")
    public List<AnnouncementDTO> getAnnouncement(@RequestParam String mode) {
        if (mode.equals("active")) {
            List<Announcement> announcementList = announcementService.getAnnouncementActive("Y");
            List<AnnouncementDTO> announcementDTOList =
                    announcementList.stream()
                            .map(a -> modelMapper.map(a, AnnouncementDTO.class))
                            .collect(Collectors.toList());
            return announcementDTOList;
        } else if(mode.equals("close")){
            List<Announcement> announcementList = announcementService.getAnnouncementActive("N");
            List<AnnouncementDTO> announcementDTOList =
                    announcementList.stream()
                            .map(a -> modelMapper.map(a, AnnouncementDTO.class))
                            .collect(Collectors.toList());
            return announcementDTOList;
        }else{
            List<Announcement> announcementList = announcementService.getAnnouncement();
            List<AnnouncementDTO> announcementDTOList =
                    announcementList.stream()
                            .map(a -> modelMapper.map(a, AnnouncementDTO.class))
                            .collect(Collectors.toList());
            return announcementDTOList;
        }
    }


    @GetMapping("/announcements/{id}")
    public AnnouncementByIdDTO getAnnouncementById(@PathVariable Integer id) {
        Announcement announcement = announcementService.getAnnouncementById(id);
        AnnouncementByIdDTO announcementDTO = modelMapper.map(announcement, AnnouncementByIdDTO.class);
        return announcementDTO;

    }

//    @GetMapping("/announcements/list")
//    public List<AddAnnouncementDTO> getAllAnnouncement() {
//        List<Announcement> announcement = announcementService.getAllAnnouncement();
//        List<AddAnnouncementDTO> addAnnouncementDTO =
//                announcement.stream()
//                        .map(a -> modelMapper.map(a, AddAnnouncementDTO.class))
//                        .collect(Collectors.toList());
//        return addAnnouncementDTO;
//    }

    @PostMapping("/announcements")
    public AnnouncementByIdDTO addAnnoucement(@RequestBody AddAnnouncementDTO newAnnoucement) {
        Announcement announcement = modelMapper.map(newAnnoucement, Announcement.class);
        announcement.setCategory(categoryService.getCategoryById(announcement.getCategory().getCategoryId()));
        announcementService.addAnnouncement(announcement);
        AnnouncementByIdDTO announcementByIdDTO = modelMapper.map(announcement, AnnouncementByIdDTO.class);
        return announcementByIdDTO;
    }

    @DeleteMapping("/announcements/{id}")
    public void delete(@PathVariable Integer id) {
        announcementService.deleteAnnouncement(id);
    }

    @PutMapping("/announcements/{id}")
    public AddAnnouncementDTO updateAnnouncement(@RequestBody AddAnnouncementDTO updateAnnouncement, @PathVariable int id) {
        AddAnnouncementDTO announcement = modelMapper.map(announcementService.getAnnouncementById(id), AddAnnouncementDTO.class);
        return announcementService.updateAnnouncement(announcement, updateAnnouncement);
    }
}
