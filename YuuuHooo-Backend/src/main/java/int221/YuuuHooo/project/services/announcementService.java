package int221.YuuuHooo.project.services;

import int221.YuuuHooo.project.dtos.AddAnnouncementDTO;
import int221.YuuuHooo.project.dtos.AnnouncementDTO;
import int221.YuuuHooo.project.dtos.PageDTO;
import int221.YuuuHooo.project.entities.Announcement;
import int221.YuuuHooo.project.repositories.announcementRepository;
import int221.YuuuHooo.project.repositories.categoryRepository;
import int221.YuuuHooo.project.utils.ListMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class announcementService {
    @Autowired
    private announcementRepository announcementRepository;

    @Autowired
    private categoryRepository categoryRepository;

    @Autowired
    private categoryService categoryService;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ListMapper listMapper;


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


//    public List<Announcement> getAllAnnouncement(){
//        return announcementRepository.findAll();
//    }

    public Announcement addAnnouncement(Announcement newAnnouncement){
        return announcementRepository.saveAndFlush(newAnnouncement);
    }


    public void deleteAnnouncement(int id){
        announcementRepository.findById(id).orElseThrow(()->
                new ResponseStatusException(
                        HttpStatusCode.valueOf(404),
                        "Announcement id " + id + " does not exist"
                ));
        announcementRepository.deleteById(id);
    }

//    public AddAnnouncementDTO updateAnnouncement(AddAnnouncementDTO beforeAnnouncement, AddAnnouncementDTO afterAnnouncement){
//        beforeAnnouncement.setAnnouncementTitle(afterAnnouncement.getAnnouncementTitle());
//        beforeAnnouncement.setAnnouncementDescription(afterAnnouncement.getAnnouncementDescription());
//        beforeAnnouncement.setCategoryId(afterAnnouncement.getCategoryId());
//        beforeAnnouncement.setPublishDate(afterAnnouncement.getPublishDate());
//        beforeAnnouncement.setCloseDate(afterAnnouncement.getCloseDate());
//        beforeAnnouncement.setAnnouncementDisplay(afterAnnouncement.getAnnouncementDisplay());
//        Announcement announcement = modelMapper.map(beforeAnnouncement,Announcement.class);
//        return modelMapper.map(announcementRepository.saveAndFlush(announcement),AddAnnouncementDTO.class);
//    }

    public AddAnnouncementDTO updateAnnouncement(AddAnnouncementDTO beforeAnnouncement, AddAnnouncementDTO afterAnnouncement){
        beforeAnnouncement.setAnnouncementTitle(afterAnnouncement.getAnnouncementTitle());
        beforeAnnouncement.setAnnouncementDescription(afterAnnouncement.getAnnouncementDescription());
        beforeAnnouncement.setCategoryId(afterAnnouncement.getCategoryId());
        beforeAnnouncement.setPublishDate(afterAnnouncement.getPublishDate());
        beforeAnnouncement.setCloseDate(afterAnnouncement.getCloseDate());
        beforeAnnouncement.setAnnouncementDisplay(afterAnnouncement.getAnnouncementDisplay());
        Announcement announcement = modelMapper.map(beforeAnnouncement, Announcement.class);
        return modelMapper.map(announcementRepository.saveAndFlush(announcement),AddAnnouncementDTO.class);
    }

    public Page<Announcement> getAnnouncementWithPaging(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return  announcementRepository.findAll(pageable);

    }

    public PageDTO<AnnouncementDTO> listToPage(List<Announcement> announcements , int page, int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), announcements.size());

        Page<Announcement> announcementPage = new PageImpl<>(announcements.subList(start, end), pageable, announcements.size());
        return listMapper.toPageDTO(announcementPage, AnnouncementDTO.class, modelMapper);

    }



 }
