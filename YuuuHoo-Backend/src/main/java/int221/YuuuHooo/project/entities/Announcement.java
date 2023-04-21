package int221.YuuuHooo.project.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "announcement")
public class Announcement {
    @Id
    private int idAnnouncement;
    private String announcementTitle;
    private String announcementDescription;
    private Timestamp publishDate;
    private Timestamp closeDate;
    private enum announcementDisplay {Y, N};

    @ManyToOne
    @JoinColumn(name = "announcementCategory" )
    private Category announcementCategory;
}
