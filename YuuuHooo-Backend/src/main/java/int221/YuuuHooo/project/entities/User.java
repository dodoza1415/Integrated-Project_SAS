package int221.YuuuHooo.project.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;

@Getter
@Setter
@Entity
@Table(name = "users",  uniqueConstraints= @UniqueConstraint(columnNames={"id","username", "name", "email"}))
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    private String username ;
    private String name ;
    private String email ;
    private String role ;
    @Column(name = "createdOn", insertable = false, updatable = false )
    private ZonedDateTime createdOn ;
    @Column(name = "updatedOn", insertable = false, updatable = false )
    private ZonedDateTime updatedOn ;

}