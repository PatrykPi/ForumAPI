package com.forum.ForumAPI.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NotBlank(message = "username is required.")
@Size(message="username must contain at least 3 letters", min = 3)
@Constraint(validatedBy = {})
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Username {
	
	public String message() default "Invalid Username";
	
	public Class<?>[] groups() default {};
	
	public Class<? extends Payload>[] payload() default {};
}
