package com.forum.ForumAPI.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NotBlank(message = "password is required")
@Size(message="username must contain at least 6 letters", min = 6)
@Constraint(validatedBy = UserPasswordConstraintValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UserPassword {

	public String message() default "password must contain at least one capital letter and one number";
	
	public Class<?>[] groups() default {};
	
	public Class<? extends Payload>[] payload() default {};
}
