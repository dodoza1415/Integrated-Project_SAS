package int221.YuuuHooo.project.Validation;

import int221.YuuuHooo.project.entities.User;
import int221.YuuuHooo.project.repositories.userRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueValidator implements ConstraintValidator<Unique, String> {

    @Autowired
    private userRepository userRepository;

    private String field;

    @Override
    public void initialize(Unique constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);

        field = constraintAnnotation.field();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(field.equals("username")){
            User userObj = userRepository.findByUsername(value);
            if(userObj != null){
                return false;
            }
        }

        if(field.equals("name")){
            User userObj = userRepository.findByName(value);
            if(userObj != null){
                return false;
            }
        }

        if(field.equals("email")){
            User userObj = userRepository.findByEmail(value);
            if(userObj != null){
                return false;
            }
        }

        return true;
    }
}
