package xyz.catequest.spring.global.Regexp;

public interface RegularExpression {
  String EMAIL_ROLE = "^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
  String PASSWORD_ROLE = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,25}$";
}
