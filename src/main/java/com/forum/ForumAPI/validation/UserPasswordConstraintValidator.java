package com.forum.ForumAPI.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserPasswordConstraintValidator implements ConstraintValidator<UserPassword, String>{
	
	final String REGEXP_AT_LEAST_ONE_CAPITAL_LETTER_ONE_NUMBER = "^.*(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z]).*$";

	@Override
	public boolean isValid(String password, ConstraintValidatorContext context) {
		return password.matches(REGEXP_AT_LEAST_ONE_CAPITAL_LETTER_ONE_NUMBER);
	}
}
