package int221.nw2.project.yuuuhooo.dtos;


import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
public class UserHidePasswordDTO {

    private int id;
    private String username ;
    private String name ;
    private String email ;
    private String role ;
    private ZonedDateTime createdOn ;
    private ZonedDateTime updatedOn ;
}
