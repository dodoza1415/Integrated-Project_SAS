package int221.YuuuHooo.project.services;

import int221.YuuuHooo.project.dtos.AddAnnouncementDTO;
import int221.YuuuHooo.project.dtos.AnnouncementByIdDTO;
import int221.YuuuHooo.project.entities.Announcement;
import int221.YuuuHooo.project.repositories.announcementRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
    private ModelMapper modelMapper;


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

    public List<Announcement> getAllAnnouncement(){
        return announcementRepository.findAll();
    }

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
        Announcement announcement = modelMapper.map(beforeAnnouncement,Announcement.class);
        return modelMapper.map(announcementRepository.saveAndFlush(announcement),AddAnnouncementDTO.class);
    }
}
