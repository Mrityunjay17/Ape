package Utility;

import javafx.scene.control.ComboBox;

public interface ComboBoxChecker {

    boolean comboBoxNotNull(ComboBox<String>... field);
    void comboBoxOnValueChange(ComboBox<String>... field);
}
