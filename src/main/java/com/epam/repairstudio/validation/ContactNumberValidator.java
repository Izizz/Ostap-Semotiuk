package com.epam.repairstudio.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ContactNumberValidator implements
        ConstraintValidator<ContactnumberConstraint, String> {

    @Override
    public void initialize(ContactnumberConstraint contactNumber) {
    }

    @Override
    public boolean isValid(String contactField,
                           ConstraintValidatorContext cxt) {
        return contactField != null && contactField.matches("^(?:\\+38)?(0\\d{9})$");
    }

}