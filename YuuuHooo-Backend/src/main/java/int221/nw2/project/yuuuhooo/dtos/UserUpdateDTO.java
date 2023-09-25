package int221.nw2.project.yuuuhooo.dtos;

import int221.nw2.project.yuuuhooo.validations.Unique;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateDTO {
    @Unique(field = "username")
    @NotBlank
    @Size(min = 1, max = 45)
    private String username ;
    @Unique(field = "name")
    @NotBlank
    @Size(min = 1, max = 100)
    private String name ;
    @Unique(field = "email")
    @NotBlank
    @Email(message = "Email should be valid")
    @Size(min = 1, max = 150)
    private String email ;
    @NotBlank
    private String role ;
}
