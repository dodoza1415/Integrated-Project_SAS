package int221.YuuuHooo.project.dtos;


import com.fasterxml.jackson.annotation.JsonIgnore;
import int221.YuuuHooo.project.entities.Category;
import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.SimpleTimeZone;

@Getter
@Setter
public class AnnouncementDTO {
    private int id;
    private String announcementTitle;
    private ZonedDateTime publishDate;
    private ZonedDateTime closeDate;
    private String announcementDisplay;
    private String announcementCategory;
    private int viewCount;

}
