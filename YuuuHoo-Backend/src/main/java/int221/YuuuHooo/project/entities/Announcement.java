package int221.YuuuHooo.project.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.ZonedDateTime;


@Getter
@Setter
@Entity
@Table(name = "announcements")
public class Announcement {
    @Id
    private int idAnnouncement;
    private String announcementTitle;
    private String announcementDescription;
    private ZonedDateTime publishDate;
    private ZonedDateTime closeDate;
    private String announcementDisplay;

    @ManyToOne
    @JoinColumn(name = "announcementCategory")
    private Category announcementCategory;
}
