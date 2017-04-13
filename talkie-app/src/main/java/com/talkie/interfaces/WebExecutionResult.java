package com.talkie.interfaces;

import java.util.List;

public interface WebExecutionResult {
    List<RequestError> getErrors();
    Object getData();
}
