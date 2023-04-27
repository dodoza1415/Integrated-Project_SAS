package int221.YuuuHooo.project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(name = "idAnnouncement")
    private int id;
    @Column(name = "announcementTitle")
    private String title;
    @Column(name = "announcementDescription")
    private String description;
    private ZonedDateTime publishDate;
    private ZonedDateTime closeDate;
    @Column(name = "announcementDisplay")
    private String display;

    @ManyToOne
    @JoinColumn(name = "announcementCategory")
    private Category category;
}
