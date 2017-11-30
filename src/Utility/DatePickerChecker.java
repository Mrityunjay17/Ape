package Utility;

import javafx.scene.control.DatePicker;

public interface DatePickerChecker {
    boolean datePickerNotNull(DatePicker datePicker);
    void datePickerOnDateChange(DatePicker datePicker);
}
