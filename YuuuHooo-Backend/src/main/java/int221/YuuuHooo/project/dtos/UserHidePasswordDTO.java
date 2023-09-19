package int221.YuuuHooo.project.dtos;

import int221.YuuuHooo.project.Validation.Unique;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
