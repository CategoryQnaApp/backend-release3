package xyz.catequest.spring.global.Regexp;

public interface RegularExpression {
  String EMAIL_ROLE =
      "^(?![.])(?!.*[.]{2})[A-Za-z0-9+_.-]+(?<![.])@"
          + "[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*\\.[A-Za-z]{2,}$";
  String PASSWORDS_ROLE = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,25}$";
}
