package int221.YuuuHooo.project.dtos;


import com.fasterxml.jackson.annotation.JsonIgnore;
import int221.YuuuHooo.project.entities.Category;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
public class AnnouncementByIdDTO {
    private int id;
    private String announcementTitle;
    private String announcementDescription;
    private ZonedDateTime publishDate;
    private ZonedDateTime closeDate;
    private String announcementDisplay;
    private String announcementCategory;
    private int categoryId;
    private int viewCount;
}
