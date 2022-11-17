package com.epam.repairstuidiospring.service.validation;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ContactnNumberValidator implements
        ConstraintValidator<ContactnumberConstraint, String> {

    @Override
    public void initialize(ContactnumberConstraint contactNumber) {
    }

    @Override
    public boolean isValid(String contactField,
                           ConstraintValidatorContext cxt) {
        return contactField != null && contactField.matches("[0-9]+")
                && (contactField.length() > 8) && (contactField.length() < 14);
    }

}