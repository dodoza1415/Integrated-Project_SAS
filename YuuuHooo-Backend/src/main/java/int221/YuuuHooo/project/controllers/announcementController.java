package int221.YuuuHooo.project.controllers;

import int221.YuuuHooo.project.dtos.AddAnnouncementDTO;
import int221.YuuuHooo.project.dtos.AnnouncementByIdDTO;
import int221.YuuuHooo.project.dtos.AnnouncementDTO;
import int221.YuuuHooo.project.dtos.PageDTO;
import int221.YuuuHooo.project.entities.Announcement;
import int221.YuuuHooo.project.entities.Category;
import int221.YuuuHooo.project.repositories.announcementRepository;
import int221.YuuuHooo.project.services.announcementService;
import int221.YuuuHooo.project.services.categoryService;
import int221.YuuuHooo.project.utils.ListMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:5173", "http://intproj22.sit.kmutt.ac.th"})
public class announcementController {
    @Autowired
    private announcementService announcementService;

    @Autowired
    private announcementRepository announcementRepository;

    @Autowired
    private categoryService categoryService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ListMapper listMapper;


    @GetMapping("/announcements")
    public List<AnnouncementDTO> getAnnouncement(@RequestParam(defaultValue = "admin") String mode) {
        List<Announcement> announcementBase = announcementService.getAnnouncement();
        ZonedDateTime today = ZonedDateTime.now(ZoneId.of("UTC"));
        if (mode.equals("active")) {
            List<Announcement> announcementsFilter = new ArrayList<>(); // []
            announcementBase.stream()
                    .forEach(a -> {
                        if (a.getPublishDate() == null && a.getCloseDate() == null) {
                            announcementsFilter.add(a);
                        } else if (a.getPublishDate() == null && a.getCloseDate() != null) {
                            if (today.toEpochSecond() < a.getCloseDate().toEpochSecond()) {
                                announcementsFilter.add(a);
                            }
                        } else if (a.getCloseDate() == null && a.getPublishDate() != null) {
                            if (today.toEpochSecond() >= a.getPublishDate().toEpochSecond()) {
                                announcementsFilter.add(a);
                            }
                        } else if (a.getPublishDate() != null && a.getCloseDate() != null) {
                            if (today.toEpochSecond() >= a.getPublishDate().toEpochSecond() && today.toEpochSecond() < a.getCloseDate().toEpochSecond()) {
                                announcementsFilter.add(a);
                            }
                        }
                    });
            List<AnnouncementDTO> announcementDTOList =
                    announcementsFilter.stream()
                            .filter(a -> a.getAnnouncementDisplay().contains("Y"))
                            .map(a -> modelMapper.map(a, AnnouncementDTO.class))
                            .collect(Collectors.toList());
            System.out.println(announcementsFilter.size());
            return announcementDTOList;

        } else if (mode.equals("close")) {
            List<Announcement> announcementsFilter = new ArrayList<>();
            announcementBase.stream()
                    .forEach(a -> {
                        if (a.getCloseDate() != null) {
                            if (today.toEpochSecond() >= a.getCloseDate().toEpochSecond()) {
                                announcementsFilter.add(a);
                            }
                        }
                    });
            List<AnnouncementDTO> announcementDTOList =
                    announcementsFilter.stream()
                            .filter(a -> a.getAnnouncementDisplay().contains("Y"))
                            .map(a -> modelMapper.map(a, AnnouncementDTO.class))
                            .collect(Collectors.toList());
            return announcementDTOList;
        } else {
            List<AnnouncementDTO> announcementDTOList =
                    announcementBase.stream()
                            .map(a -> modelMapper.map(a, AnnouncementDTO.class))
                            .collect(Collectors.toList());
            return announcementDTOList;
        }

    }

    @GetMapping("/announcements/pages")
    public PageDTO<AnnouncementDTO> getAnnouncementPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "admin") String mode,
            @RequestParam(defaultValue = "0") int category) {

        List<Announcement> announcementBase = announcementService.getAnnouncement();
        ZonedDateTime today = ZonedDateTime.now(ZoneId.of("UTC"));
        if (mode.equals("active")) {
            List<Announcement> announcementsFilter = new ArrayList<>();
            announcementBase.stream()
                    .forEach(a -> {
                        if (a.getPublishDate() == null && a.getCloseDate() == null) {
                            announcementsFilter.add(a);
                        } else if (a.getPublishDate() == null && a.getCloseDate() != null) {
                            if (today.toEpochSecond() < a.getCloseDate().toEpochSecond()) {
                                announcementsFilter.add(a);
                            }
                        } else if (a.getCloseDate() == null && a.getPublishDate() != null) {
                            if (today.toEpochSecond() >= a.getPublishDate().toEpochSecond()) {
                                announcementsFilter.add(a);
                            }
                        } else if (a.getPublishDate() != null && a.getCloseDate() != null) {
                            if (today.toEpochSecond() >= a.getPublishDate().toEpochSecond() && today.toEpochSecond() < a.getCloseDate().toEpochSecond()) {
                                announcementsFilter.add(a);
                            }
                        }
                    });
            if (category != 0) {
                String categoryFilter = categoryService.getCategoryName(category);
                List<Announcement> announcements =
                        announcementsFilter.stream()
                                .filter(ann -> ann.getCategory().getCategoryName().contains(categoryFilter))
                                .filter(ann -> ann.getAnnouncementDisplay().contains("Y"))
                                .collect(Collectors.toList());

                return announcementService.listToPage(announcements, page, size);

            } else {
                List<Announcement> announcements =
                        announcementsFilter.stream()
                                .filter(a -> a.getAnnouncementDisplay().contains("Y"))
                                .collect(Collectors.toList());

                return announcementService.listToPage(announcements, page, size);
            }

        } else if (mode.equals("close")) {
            List<Announcement> announcementsFilter = new ArrayList<>();
            announcementBase.stream()
                    .forEach(a -> {
                        if (a.getCloseDate() != null) {
                            if (today.toEpochSecond() >= a.getCloseDate().toEpochSecond()) {
                                announcementsFilter.add(a);
                            }
                        }
                    });
            if(category != 0){
                String categoryFilter = categoryService.getCategoryName(category);
                List<Announcement> announcements =
                        announcementsFilter.stream()
                                .filter(a -> a.getAnnouncementDisplay().contains("Y"))
                                .filter(a -> a.getCategory().getCategoryName().contains(categoryFilter))
                                .collect(Collectors.toList());

                return announcementService.listToPage(announcements, page, size);

            }else {
                List<Announcement> announcements =
                        announcementsFilter.stream()
                                .filter(a -> a.getAnnouncementDisplay().contains("Y"))
                                .collect(Collectors.toList());

                return announcementService.listToPage(announcements, page, size);
            }

        } else {
            Page<Announcement> announcementPage = announcementService.getAnnouncementWithPaging(page, size);
            return listMapper.toPageDTO(announcementPage, AnnouncementDTO.class, modelMapper);
        }
    }



    @GetMapping("/announcements/{id}")
    public AnnouncementByIdDTO getAnnouncementById(@PathVariable Integer id, @RequestParam(defaultValue = "false") boolean count ) {
        if(count == true){
            Announcement announcement = announcementService.getAnnouncementById(id);
            announcement.setViewCount(announcement.getViewCount() + 1);
            announcementRepository.saveAndFlush(announcement);
            AnnouncementByIdDTO announcementDTO = modelMapper.map(announcement, AnnouncementByIdDTO.class);
            return announcementDTO;
        }else {
            Announcement announcement = announcementService.getAnnouncementById(id);
            AnnouncementByIdDTO announcementDTO = modelMapper.map(announcement, AnnouncementByIdDTO.class);
            return announcementDTO;

        }


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
    public AddAnnouncementDTO updateAnnouncement(@RequestBody AddAnnouncementDTO updateAnnouncement,
                                                 @PathVariable int id) {
        AddAnnouncementDTO announcement = modelMapper.map(announcementService.getAnnouncementById(id), AddAnnouncementDTO.class);
        return announcementService.updateAnnouncement(announcement, updateAnnouncement);
    }


}