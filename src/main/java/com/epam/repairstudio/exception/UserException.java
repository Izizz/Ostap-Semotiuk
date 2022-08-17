package com.epam.repairstudio.exception;

import com.epam.repairstudio.model.enums.ErrorType;

public class UserException extends ServiceException {
    private static final String DEFAULT_MESSAGE = "User not found";

    public UserException() {
        super(DEFAULT_MESSAGE);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.VALIDATION_ERROR_TYPE;
    }
}
