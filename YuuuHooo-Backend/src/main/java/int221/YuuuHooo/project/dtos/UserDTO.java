package int221.YuuuHooo.project.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;


@Getter
@Setter
public class UserDTO {
    @NotBlank
    @Size(min = 1, max = 45)
    private String username ;
    @NotBlank
    @Size(min = 1, max = 100)
    private String name ;
    @NotBlank
    @Email
    @Size(min = 1, max = 150)
    private String email ;
    @NotBlank
    private String role ;
}