package xyz.catequest.spring.global.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Pattern;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({ FIELD, PARAMETER })
@Retention(RUNTIME)
@Constraint(validatedBy = {})
@Documented
@Pattern(regexp = "^(?![.])(?!.*[.]{2})[A-Za-z0-9+_.-]+(?<![.])@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*\\.[A-Za-z]{2,}$")
public @interface ValidEmail {
  String message() default "올바르지 않은 이메일 형식입니다.";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}
