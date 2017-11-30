package Utility;

import javafx.scene.control.TextField;

public interface TextFieldVerify extends Resource {
    void textFieldVerifyOnTextChange(TextField... field);
    boolean textFieldNotNull(TextField... Field);
}
