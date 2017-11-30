package Utility;

import controls.NumberField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Verification implements EmailVerify,MobileVerify,PasswodVerify,DatePickerChecker,ComboBoxChecker,TextFieldVerify{

    @Override
    public void emailVerifyOnTextChange(TextField emailId) {
        emailId.textProperty().addListener(((observable, oldValue, newValue) -> {
            if(newValue.matches(Email_Pattern)){
                emailId.setStyle(Remove_border);
            }
            else {
                emailId.setStyle(Add_border);
            }
        }));
    }

    @Override
    public boolean emailVerify(TextField emailId) {
        if(emailId.getText().matches(Email_Pattern)){
            return true;
        }
        emailId.setStyle(Add_border);
        return false;
    }

    @Override
    public void mobileVerifyOnNumberChange(NumberField Mobile) {
        Mobile.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.length()>10){
                Mobile.setText(oldValue);
                Mobile.setStyle(Remove_border);
            }
            else if(newValue.matches(Mobile_Pattern)){
                Mobile.setStyle(Remove_border);
            }
            else {
                Mobile.setStyle(Add_border);
            }
        });
    }

    @Override
    public boolean mobileVerify(NumberField numberField) {

        if(numberField.getText().length()==10){
            return true;
        }
        numberField.setStyle(Add_border);
        return false;
    }

    @Override
    public boolean datePickerNotNull(DatePicker field) {

        if(field.getValue()==null){
            field.setStyle(Add_border);
            return false;
        }
        return true;
    }

    @Override
    public void datePickerOnDateChange(DatePicker datePicker) {
        datePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            datePicker.setStyle(Remove_border);

        });
    }

    @Override
    public boolean comboBoxNotNull(ComboBox<String>... field) {
        boolean notNull=true;

        for(ComboBox<String> f:field){
            if(f.getSelectionModel().isEmpty()){
                f.setStyle(Add_border);
                notNull=false;
            }
        }
        return notNull;
    }

    @Override
    public void comboBoxOnValueChange(ComboBox<String>... field) {

        for (ComboBox<String> f:field){
            f.valueProperty().addListener((observable, oldValue, newValue) -> {
                f.setStyle(Remove_border);
            });
        }

    }

    @Override
    public void passwordFieldVerifyOnTextChange(PasswordField field) {
        field.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.length()<6){
                field.setStyle(Add_border);
            }
            else {
                field.setStyle(Remove_border);
            }
        });
    }

    @Override
    public void confirmPasswordFieldOnTextChange(PasswordField passwordField, PasswordField confirmPasswordField) {
        confirmPasswordField.textProperty().addListener((observable, oldValue, newValue) -> {
            if(passwordField.getText().equals(confirmPasswordField.getText())){
                confirmPasswordField.setStyle(Remove_border);
            }
            else {
                confirmPasswordField.setStyle(Add_border);
            }
        });
    }

    @Override
    public boolean passwordVerify(PasswordField passwordField, PasswordField confirmPasswordField) {
        if(passwordField.getText().length()>=6 &&passwordField.getText().equals(confirmPasswordField.getText())){
            return true;
        }
        passwordField.setText("");
        confirmPasswordField.setText("");
        passwordField.setStyle(Add_border);
        confirmPasswordField.setStyle(Add_border);
        return false;
    }

    @Override
    public void textFieldVerifyOnTextChange(TextField... field) {

        for(TextField f:field){
            f.textProperty().addListener(((observable, oldValue, newValue) -> {
                if(newValue.length()>0){
                    f.setStyle(Remove_border);
                }
                else {
                    f.setStyle(Add_border);
                }
            }));
        }
    }

    @Override
    public boolean textFieldNotNull(TextField... Field) {
        boolean notNull=true;
        for(TextField f:Field){
            if(f.getText().equals("")){
                f.setStyle(Add_border);
                notNull=false;
            }
        }
        return notNull;
    }
}