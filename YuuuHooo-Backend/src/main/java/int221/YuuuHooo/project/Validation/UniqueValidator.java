package int221.YuuuHooo.project.Validation;

import int221.YuuuHooo.project.entities.User;
import int221.YuuuHooo.project.repositories.userRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerMapping;

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
        if(request.getMethod().equals("POST")){
            System.out.println(request.getMethod());
            if(findByField(value) != null){
                return false;
            }
        }

        if(request.getMethod().equals("PUT")){
//            System.out.println(request.getMethod());
            //ค่าเก่า เทียบ ค่าใหม่
            if(findByField(value) != null){
                return false;
            }
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
