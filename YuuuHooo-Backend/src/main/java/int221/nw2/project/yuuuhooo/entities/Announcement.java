package int221.nw2.project.yuuuhooo.entities;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAnnouncement")
    private int id;
    private String announcementTitle;
    private String announcementDescription;
    private ZonedDateTime publishDate;
    private ZonedDateTime closeDate;
    private String announcementDisplay;

    @ManyToOne
    @JoinColumn(name = "announcementCategory")
    private Category category;

    private int viewCount;
}
