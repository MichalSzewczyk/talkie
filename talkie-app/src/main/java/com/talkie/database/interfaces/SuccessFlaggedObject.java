package com.talkie.database.interfaces;

public abstract class SuccessFlaggedObject {
    transient private Boolean success = true;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
