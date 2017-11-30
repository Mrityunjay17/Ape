package Utility;

import javafx.scene.control.PasswordField;

public interface PasswodVerify {
    void passwordFieldVerifyOnTextChange(PasswordField field);
    void confirmPasswordFieldOnTextChange(PasswordField passwordField,PasswordField confirmPasswordField);
    boolean passwordVerify(PasswordField passwordField,PasswordField confirmPasswordField);
}
