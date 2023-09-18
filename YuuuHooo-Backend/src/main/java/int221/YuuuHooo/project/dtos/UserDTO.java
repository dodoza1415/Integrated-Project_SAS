package int221.YuuuHooo.project.dtos;

import int221.YuuuHooo.project.Validation.Unique;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserDTO {
    @Unique(field = "username", message = "does not unique")
    @NotBlank
    @Size(min = 1, max = 45)
    private String username ;
    @Unique(field = "name", message = "does not unique")
    @NotBlank
    @Size(min = 1, max = 100)
    private String name ;
    @Unique(field = "email", message = "does not unique")
    @NotBlank
    @Email(message = "Email should be valid")
    @Size(min = 1, max = 150)
    private String email ;
    @NotBlank
    private String role ;
}