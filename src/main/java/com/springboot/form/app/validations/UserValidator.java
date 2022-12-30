package com.springboot.form.app.validations;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.springboot.form.app.models.User;

@Component
public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "NotEmpty.user.nombre");
		
//		if (!user.getId().matches("[0-9]{2}[.,][\\d]{3}[.,][\\d]{3}[-][A-Z]{1}")) {
//			errors.rejectValue("id", "Pattern.user.id");
//		}
	}

}
