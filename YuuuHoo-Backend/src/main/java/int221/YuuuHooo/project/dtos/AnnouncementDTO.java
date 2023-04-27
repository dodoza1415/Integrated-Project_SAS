package int221.YuuuHooo.project.dtos;


import com.fasterxml.jackson.annotation.JsonIgnore;
import int221.YuuuHooo.project.entities.Category;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
public class AnnouncementDTO {
    private int id;
    private String title;
    private String description;
    private ZonedDateTime publishDate;
    private ZonedDateTime closeDate;
    private String display;
    private String categoryName;
}
