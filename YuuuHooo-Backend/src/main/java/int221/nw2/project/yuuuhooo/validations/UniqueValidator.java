package int221.nw2.project.yuuuhooo.validations;


import int221.nw2.project.yuuuhooo.entities.User;
import int221.nw2.project.yuuuhooo.repositories.userRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
public class UniqueValidator implements ConstraintValidator<Unique, String> {

    @Autowired
    private userRepository userRepository;

    private String field;

    @Autowired
    private HttpServletRequest request;

    private User userObj;


    @Override
    public void initialize(Unique constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);

        field = constraintAnnotation.field();


    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
//        System.out.println(Id);
        if(request.getMethod().equals("POST")){
//            System.out.println(request.getMethod());
            if(findByField(value) != null){
                return false;
            }
        }

        if(request.getMethod().equals("PUT")){

            return true;
        }


        return true;
    }

    public User findByField(String value){
        if(field.equals("username")){
            userObj =  userRepository.findByUsername(value);
        } else if (field.equals("name")) {
            userObj = userRepository.findByName(value);
        } else if (field.equals("email")) {
            userObj = userRepository.findByEmail(value);
        }
        return userObj;
    }


}
