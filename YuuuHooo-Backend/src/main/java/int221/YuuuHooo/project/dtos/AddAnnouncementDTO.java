package int221.YuuuHooo.project.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import int221.YuuuHooo.project.entities.Category;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
public class AddAnnouncementDTO {
    @Id
    private int id;
    private String announcementTitle;
    private String announcementDescription;
    private int categoryId;
    private ZonedDateTime publishDate;
    private ZonedDateTime closeDate;
    private String announcementDisplay;
    private  String announcementCategory;


}
