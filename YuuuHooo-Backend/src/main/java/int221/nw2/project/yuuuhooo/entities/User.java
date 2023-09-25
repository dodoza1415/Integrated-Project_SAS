package int221.nw2.project.yuuuhooo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
    private String password;
    private String name ;
    private String email ;
    private String role ;
    @Column(name = "createdOn", insertable = false, updatable = false )
    private ZonedDateTime createdOn ;
    @Column(name = "updatedOn", insertable = false, updatable = false )
    private ZonedDateTime updatedOn ;

}