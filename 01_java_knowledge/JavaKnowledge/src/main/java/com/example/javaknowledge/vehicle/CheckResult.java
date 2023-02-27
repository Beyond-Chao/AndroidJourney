package com.example.javaknowledge.vehicle;

public class CheckResult {
    private boolean isValid;
    private String errHint;

    public boolean isValid() {
        return isValid;
    }

    public String getErrHint() {
        return errHint;
    }

    public CheckResult(boolean isValid, String errHint) {
        this.isValid = isValid;
        this.errHint = errHint;
    }


}
