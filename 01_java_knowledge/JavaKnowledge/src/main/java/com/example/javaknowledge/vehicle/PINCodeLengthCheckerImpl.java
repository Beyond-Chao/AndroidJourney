package com.example.javaknowledge.vehicle;

public class PINCodeLengthCheckerImpl implements ILengthChecker {

    private int validLength;
    private String underCheckPinCode;

    public PINCodeLengthCheckerImpl(int validLength) {
        this.validLength = validLength;
    }

    @Override
    public int validLength() {
        return validLength;
    }

    @Override
    public CheckResult checkLength(IVehicle v) {
        underCheckPinCode = v.getPINCode();
        boolean valid = (underCheckPinCode != null && underCheckPinCode.length() == validLength());
        CheckResult checkResult = new CheckResult(valid, valid ? "" : message());
        return checkResult;
    }

    @Override
    public String name() {
        return "PIN码长度检测";
    }

    @Override
    public String message() {
        return String.format("PIN码长度检测失败：%s 长度不是 %d", underCheckPinCode, validLength);
    }
}
