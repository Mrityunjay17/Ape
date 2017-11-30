package Utility;

import controls.NumberField;

public interface MobileVerify extends Resource {
    void mobileVerifyOnNumberChange(NumberField Mobile);
    boolean mobileVerify(NumberField numberField);
}
