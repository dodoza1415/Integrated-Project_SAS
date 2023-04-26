package int221.YuuuHooo.project.dtos;


import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
public class AnnouncementDTO {
    private int idAnnouncement;
    private String announcementTitle;
    private ZonedDateTime publishDate;
    private ZonedDateTime closeDate;
    private String announcementDisplay;
}
