package Utility;

import javafx.scene.control.TextField;

public interface EmailVerify extends Resource {
    void emailVerifyOnTextChange(TextField emailId);
    boolean emailVerify(TextField emailId);
}
