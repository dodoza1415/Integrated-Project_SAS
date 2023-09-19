package int221.YuuuHooo.project.Validation;


import jakarta.persistence.Column;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.hibernate.validator.internal.constraintvalidators.hv.UniqueElementsValidator;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueValidator.class )
@Documented
public @interface Unique {
    public String message() default "does not unique" ;

    public String field() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
